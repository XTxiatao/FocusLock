package com.focuslock.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.isVisible
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.data.AppRestrictionPlanRepository
import com.focuslock.databinding.DialogWhitelistPickerBinding
import com.focuslock.databinding.OverlayViewBinding
import com.focuslock.model.LockSchedule
import com.focuslock.model.AppRestrictionPlan
import com.focuslock.model.WhitelistedApp
import com.focuslock.model.Reminder
import com.focuslock.data.ReminderRepository
import com.focuslock.ui.ReminderDayAdapter
import com.focuslock.ui.ReminderGrouping
import com.focuslock.ui.showReminderEditorDialog
import com.focuslock.ui.completeReminder
import com.focuslock.util.LockStateTracker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

private const val SERVICE_LOG_TAG = "LockOverlaySvc"

class LockOverlayService : Service() {

    private val channelId = "focus_lock_channel"
    private val notificationId = 101
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val mainHandler = Handler(Looper.getMainLooper())
    private val countdownHandler = Handler(Looper.getMainLooper())
    private var countdownRunnable: Runnable? = null
    private var overlayAdded = false
    private lateinit var windowManager: WindowManager
    private lateinit var binding: OverlayViewBinding
    private lateinit var repository: com.focuslock.data.LockScheduleRepository
    private lateinit var whitelistRepository: com.focuslock.data.WhitelistedAppRepository
    private lateinit var restrictionPlanRepository: AppRestrictionPlanRepository
    private lateinit var reminderRepository: ReminderRepository
    private lateinit var overlayContext: Context
    private var enabledSchedules = emptyList<LockSchedule>()
    private var whitelistedApps = emptyList<WhitelistedApp>()
    private var appRestrictionPlans = emptyList<AppRestrictionPlan>()
    private lateinit var windowParams: WindowManager.LayoutParams
    private var foregroundStarted = false
    private var temporaryLockExpiryMillis = 0L
    private var evaluationRunning = false
    private var nextUnlockTimeMillis = 0L
    private var currentSchedule: LockSchedule? = null
    private var activeRestrictions: Map<String, Long> = emptyMap()
    private var whitelistDialog: AlertDialog? = null
    private var overlayWhitelistAdapter: OverlayWhitelistAdapter? = null
    private var lastForegroundPackage: String? = null
    private val reminderAdapter by lazy {
        ReminderDayAdapter({ reminder -> openReminderEditorFromOverlay(reminder) }, ::completeReminderFromOverlay)
    }
    private var reminderEditorDialog: AlertDialog? = null
    private var overlayResumeAfterMillis: Long = 0L

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_LOG_TAG, "onCreate")
        promoteToForeground()
        val app = application as FocusLockApplication
        repository = app.lockScheduleRepository
        whitelistRepository = app.whitelistedAppRepository
        restrictionPlanRepository = app.appRestrictionPlanRepository
        reminderRepository = app.reminderRepository
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        overlayContext = ContextThemeWrapper(this, R.style.Theme_FocusLock)
        binding = OverlayViewBinding.inflate(LayoutInflater.from(overlayContext))
        prepareOverlay()
        setCountdownText(formatDuration(0))

        serviceScope.launch {
            repository.enabledSchedulesFlow.collect { schedules ->
                enabledSchedules = schedules
                Log.d(
                    SERVICE_LOG_TAG,
                    "Enabled plans updated: ${schedules.joinToString { it.debugDescription() }}"
                )
                evaluateSchedule()
            }
        }

        serviceScope.launch {
            whitelistRepository.whitelistFlow.collect { apps ->
                whitelistedApps = apps
                Log.d(SERVICE_LOG_TAG, "Whitelist updated: ${apps.map { it.packageName }}")
                evaluateSchedule()
            }
        }

        serviceScope.launch {
            restrictionPlanRepository.plansFlow.collect { plans ->
                appRestrictionPlans = plans
                Log.d(
                    SERVICE_LOG_TAG,
                    "Restriction plans updated: ${plans.map { it.id to it.apps.map(WhitelistedApp::packageName) }}"
                )
                evaluateSchedule()
            }
        }

        serviceScope.launch {
            reminderRepository.remindersFlow.collect { reminders ->
                val active = reminders.filter { !it.isCompleted }
                val (timed, floating) = active.partition { it.anchorDateTimeMillis != null }
                val groups = ReminderGrouping.buildDayGroups(timed) + ReminderGrouping.buildFloatingGroup(floating)
                mainHandler.post {
                    reminderAdapter.submitList(groups)
                    binding.overlayReminderEmpty.isVisible = groups.isEmpty()
                }
            }
        }
    }

    private fun prepareOverlay() {
        binding.root.setOnTouchListener { _, _ -> true }
        binding.openWhitelistButton.setOnClickListener { showWhitelistAppDialog() }
        binding.forceUnlockButton.setOnClickListener { showForceUnlockDialog() }
        binding.addReminderFab.setOnClickListener { openReminderEditorFromOverlay(null) }

        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        windowParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            type,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                    WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }

        binding.overlayReminderRecycler.layoutManager = LinearLayoutManager(overlayContext)
        binding.overlayReminderRecycler.adapter = reminderAdapter
        binding.overlayReminderEmpty.isVisible = true
    }

    private fun evaluateSchedule() {
        val tempActive = isTemporaryLockActive()
        val now = LocalDateTime.now()
        activeRestrictions = computeActiveRestrictions(now)

        if (!tempActive && enabledSchedules.isEmpty() && activeRestrictions.isEmpty()) {
            Log.d(
                SERVICE_LOG_TAG,
                "No active schedules, temporary lock, or app restrictions; keeping service idle"
            )
            hideOverlay()
            stopCountdown()
            LockStateTracker.enforceHome = false
            refreshWhitelistDialog()
            return
        }

        val activeSchedule = enabledSchedules.firstOrNull { it.matches(now) }
        val scheduleActive = activeSchedule != null
        val overlayState = if (overlayAdded) "visible" else "hidden"
        val timeLabel = String.format("%02d:%02d:%02d", now.hour, now.minute, now.second)
        val foregroundPackage = resolveForegroundPackage()
        if (!foregroundPackage.isNullOrEmpty()) {
            LockStateTracker.currentForegroundPackage = foregroundPackage
        }
        val foregroundWhitelisted = isPackageWhitelisted(foregroundPackage)
        val foregroundRestricted = foregroundPackage != null && activeRestrictions.containsKey(foregroundPackage)
        Log.d(
            SERVICE_LOG_TAG,
            "Evaluating now=$timeLabel tempActive=$tempActive scheduleActive=$scheduleActive overlay=$overlayState enabledCount=${enabledSchedules.size} " +
                "foreground=${foregroundPackage ?: "unknown"} whitelisted=$foregroundWhitelisted restricted=$foregroundRestricted restrictions=${activeRestrictions.size}"
        )
        currentSchedule = activeSchedule
        if (foregroundWhitelisted && !foregroundRestricted) {
            Log.d(SERVICE_LOG_TAG, "Foreground app is whitelisted, suppressing overlay")
            hideOverlay()
            stopCountdown()
            LockStateTracker.enforceHome = false
            refreshWhitelistDialog()
            return
        }

        val shouldLock = tempActive || scheduleActive || foregroundRestricted
        LockStateTracker.enforceHome = shouldLock
        val overlaySuppressed = SystemClock.elapsedRealtime() < overlayResumeAfterMillis
        if (shouldLock) {
            if (overlaySuppressed) {
                hideOverlay()
                stopCountdown()
            } else {
                showOverlay()
                val targetMillis = when {
                    tempActive -> temporaryLockExpiryMillis
                    foregroundRestricted -> activeRestrictions[foregroundPackage] ?: System.currentTimeMillis()
                    activeSchedule != null -> planEndMillis(activeSchedule, now)
                    else -> System.currentTimeMillis()
                }
                startCountdownFor(targetMillis)
            }
        } else {
            hideOverlay()
            stopCountdown()
            overlayResumeAfterMillis = 0L
        }
        refreshWhitelistDialog()
    }

    private fun showOverlay() {
        if (overlayAdded) {
            return
        }
        mainHandler.post {
            if (!overlayAdded) {
                windowManager.addView(binding.root, windowParams)
                overlayAdded = true
            }
        }
    }

    private fun hideOverlay() {
        if (!overlayAdded) {
            return
        }
        mainHandler.post {
            if (overlayAdded) {
                windowManager.removeViewImmediate(binding.root)
                overlayAdded = false
                reminderEditorDialog?.dismiss()
            }
        }
    }

    private fun startEvaluationLoop() {
        if (evaluationRunning) {
            return
        }
        evaluationRunning = true
        Log.d(SERVICE_LOG_TAG, "Starting evaluation loop")
        serviceScope.launch {
            while (isActive) {
                evaluateSchedule()
                delay(1000L)
            }
        }
    }

    private fun showWhitelistAppDialog() {
        if (whitelistedApps.isEmpty()) {
            Toast.makeText(this, "Whitelist is empty", Toast.LENGTH_SHORT).show()
            return
        }

        whitelistDialog?.dismiss()

        val dialogBinding = DialogWhitelistPickerBinding.inflate(LayoutInflater.from(this))
        val adapter = OverlayWhitelistAdapter { item ->
            if (item.isRestricted()) {
                val unlockTime = item.restrictedUntil?.let { formatUnlockClock(it) } ?: "later"
                showOverlayAlert(getString(R.string.restriction_app_not_allowed, unlockTime))
            } else {
                val launchIntent = packageManager.getLaunchIntentForPackage(item.app.packageName)
                if (launchIntent != null) {
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    hideOverlay()
                    overlayResumeAfterMillis = SystemClock.elapsedRealtime() + 1000L
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(this, "Cannot launch ${item.app.label}", Toast.LENGTH_SHORT).show()
                }
                whitelistDialog?.dismiss()
            }
        }
        adapter.submitList(buildWhitelistDialogItems())
        dialogBinding.whitelistRecycler.configureResponsiveGrid()
        dialogBinding.whitelistRecycler.adapter = adapter

        val builder = AlertDialog.Builder(
            ContextThemeWrapper(this, androidx.appcompat.R.style.Theme_AppCompat_Dialog)
        )
            .setView(dialogBinding.root)
        val dialog = builder.create()
        dialogBinding.closeButton.setOnClickListener {
            dialog.dismiss()
        }
        overlayWhitelistAdapter = adapter
        whitelistDialog = dialog
        dialog.window?.setType(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_PHONE
        )
        dialog.setOnDismissListener {
            overlayWhitelistAdapter = null
            whitelistDialog = null
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setGravity(android.view.Gravity.BOTTOM)
    }

    private fun showForceUnlockDialog() {
        val dialog = AlertDialog.Builder(overlayContext)
            .setMessage(R.string.lock_force_unlock_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ -> performForceUnlock() }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
        dialog.window?.setType(dialogWindowType())
        dialog.show()
    }

    private fun performForceUnlock() {
        serviceScope.launch {
            val activePlan = currentSchedule
            if (activePlan != null) {
                repository.update(activePlan.copy(isEnabled = false))
                Log.d(SERVICE_LOG_TAG, "Plan ${activePlan.id} disabled due to force unlock")
                mainHandler.post {
                    Toast.makeText(
                        this@LockOverlayService,
                        getString(R.string.plan_disabled_force_unlock),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            temporaryLockExpiryMillis = 0L
            hideOverlay()
            stopCountdown()
            LockStateTracker.enforceHome = false
            stopSelf()
        }
    }

    private fun refreshWhitelistDialog() {
        val items = buildWhitelistDialogItems()
        mainHandler.post {
            overlayWhitelistAdapter?.submitList(items)
        }
    }

    private fun buildWhitelistDialogItems(): List<WhitelistDialogItem> {
        val now = System.currentTimeMillis()
        return whitelistedApps.map { app ->
            val unlock = activeRestrictions[app.packageName]
            val validUnlock = unlock?.takeIf { it > now }
            WhitelistDialogItem(app, validUnlock)
        }
    }

    private fun startCountdownFor(targetMillis: Long) {
        nextUnlockTimeMillis = targetMillis
        countdownRunnable?.let { countdownHandler.removeCallbacks(it) }
        countdownRunnable = Runnable {
            val remaining = nextUnlockTimeMillis - System.currentTimeMillis()
            setCountdownText(formatDuration(remaining))
            if (remaining > 0) {
                countdownHandler.postDelayed(countdownRunnable!!, 1000L)
            }
        }.also { countdownHandler.post(it) }
    }

    private fun stopCountdown() {
        countdownRunnable?.let {
            countdownHandler.removeCallbacks(it)
        }
        countdownRunnable = null
        setCountdownText(formatDuration(0))
    }

    private fun formatDuration(remainingMillis: Long): String {
        if (remainingMillis <= 0) {
            return "00:00:00"
        }
        val seconds = remainingMillis / 1000
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, secs)
    }

    private fun formatUnlockClock(unlockMillis: Long): String {
        val localTime = Instant.ofEpochMilli(unlockMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
        return String.format("%02d:%02d", localTime.hour, localTime.minute)
    }

    private fun planEndMillis(schedule: LockSchedule, now: LocalDateTime): Long {
        return calculateEndMillis(schedule.startMinutes, schedule.endMinutes, now)
    }

    private fun calculateEndMillis(startMinutes: Int, endMinutes: Int, now: LocalDateTime): Long {
        val nextEndDay = if (startMinutes <= endMinutes) {
            now
        } else {
            val currentMinute = now.hour * 60 + now.minute
            if (currentMinute >= startMinutes) {
                now.plusDays(1)
            } else {
                now
            }
        }
        val endHour = endMinutes / 60
        val endMinute = endMinutes % 60
        val endDateTime = nextEndDay
            .withHour(endHour)
            .withMinute(endMinute)
            .withSecond(0)
            .withNano(0)
        return endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    private fun computeActiveRestrictions(now: LocalDateTime): Map<String, Long> {
        val restrictionMap = mutableMapOf<String, Long>()
        appRestrictionPlans.filter { it.matches(now) }.forEach { plan ->
            val endMillis = calculateEndMillis(plan.startMinutes, plan.endMinutes, now)
            plan.apps.forEach { app ->
                val existing = restrictionMap[app.packageName]
                if (existing == null || endMillis > existing) {
                    restrictionMap[app.packageName] = endMillis
                }
            }
        }
        return restrictionMap
    }

    private fun handleTemporaryLockIntent(intent: Intent?) {
        intent?.getIntExtra(EXTRA_TEMP_LOCK_MINUTES, 0)?.takeIf { it > 0 }?.let {
            applyTemporaryLock(it)
        }
    }

    private fun applyTemporaryLock(durationMinutes: Int) {
        temporaryLockExpiryMillis = System.currentTimeMillis() + durationMinutes * 60_000L
        serviceScope.launch {
            evaluateSchedule()
        }
        Log.d(SERVICE_LOG_TAG, "Temporary lock for $durationMinutes minutes until ${temporaryLockExpiryMillis}")
    }

    private fun setCountdownText(text: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            binding.countdownText.text = text
        } else {
            mainHandler.post { binding.countdownText.text = text }
        }
    }

    private fun resolveForegroundPackage(): String? {
        return try {
            val usageStatsManager = getSystemService(UsageStatsManager::class.java)
                ?: return lastForegroundPackage ?: LockStateTracker.currentForegroundPackage
            val end = System.currentTimeMillis()
            val begin = end - 5_000
            val usageEvents = usageStatsManager.queryEvents(begin, end)
            var latestPackage: String? = null
            var latestTimestamp = 0L
            val event = UsageEvents.Event()
            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event)
                val isForegroundEvent = event.eventType == UsageEvents.Event.ACTIVITY_RESUMED ||
                        event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND
                if (isForegroundEvent && event.timeStamp > latestTimestamp) {
                    latestTimestamp = event.timeStamp
                    latestPackage = event.packageName
                }
            }
            if (latestPackage != null) {
                lastForegroundPackage = latestPackage
            }
            lastForegroundPackage ?: LockStateTracker.currentForegroundPackage
        } catch (e: Exception) {
            Log.w(SERVICE_LOG_TAG, "Unable to resolve foreground package", e)
            lastForegroundPackage ?: LockStateTracker.currentForegroundPackage
        }
    }

    private fun isPackageWhitelisted(packageName: String?): Boolean {
        if (packageName.isNullOrEmpty()) {
            return false
        }
        return whitelistedApps.any { it.packageName == packageName }
    }

    private inner class OverlayWhitelistAdapter(
        private val clickListener: (WhitelistDialogItem) -> Unit
    ) : RecyclerView.Adapter<OverlayWhitelistAdapter.ViewHolder>() {

        private val items = mutableListOf<WhitelistDialogItem>()

        fun submitList(list: List<WhitelistDialogItem>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = com.focuslock.databinding.ItemWhitelistDialogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        inner class ViewHolder(
            private val binding: com.focuslock.databinding.ItemWhitelistDialogBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: WhitelistDialogItem) {
                val icon = runCatching { packageManager.getApplicationIcon(item.app.packageName) }.getOrNull()
                binding.appIcon.setImageDrawable(icon ?: getDrawable(android.R.drawable.sym_def_app_icon))
                binding.appLabel.text = item.app.label
                val now = System.currentTimeMillis()
                val restricted = item.isRestricted(now)
                if (restricted && item.restrictedUntil != null) {
                    val remaining = item.restrictedUntil - now
                    binding.appCountdownIcon.visibility = View.VISIBLE
                    binding.appCountdown.visibility = View.VISIBLE
                    binding.appCountdown.text = formatDuration(remaining)
                    binding.root.alpha = 0.4f
                } else {
                    binding.appCountdownIcon.visibility = View.GONE
                    binding.appCountdown.visibility = View.GONE
                    binding.root.alpha = 1f
                }
                binding.root.setOnClickListener { clickListener(item) }
            }
        }
    }

    private fun RecyclerView.configureResponsiveGrid() {
        layoutManager = GridLayoutManager(context, 4)
    }

    private data class WhitelistDialogItem(
        val app: WhitelistedApp,
        val restrictedUntil: Long?
    ) {
        fun isRestricted(now: Long = System.currentTimeMillis()): Boolean {
            return restrictedUntil != null && restrictedUntil > now
        }
    }

    private fun isTemporaryLockActive(): Boolean {
        return System.currentTimeMillis() < temporaryLockExpiryMillis
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(SERVICE_LOG_TAG, "onStartCommand startId=$startId intent=$intent")
        promoteToForeground()
        handleTemporaryLockIntent(intent)
        startEvaluationLoop()
        return START_STICKY
    }

    private fun buildNotification(): Notification {
        ensureChannel()
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle(getString(R.string.service_notification_title))
            .setContentText(getString(R.string.service_notification_text))
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setOngoing(true)
            .build()
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val manager = getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
            channelId,
            getString(R.string.service_notification_title),
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = getString(R.string.service_notification_text)
        }
        manager?.createNotificationChannel(channel)
    }

    private fun promoteToForeground() {
        if (foregroundStarted) {
            return
        }
        Log.d(SERVICE_LOG_TAG, "Promoting service to foreground")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                notificationId,
                buildNotification(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(notificationId, buildNotification())
        }
        foregroundStarted = true
    }

    override fun onDestroy() {
        Log.d(SERVICE_LOG_TAG, "onDestroy")
        hideOverlay()
        whitelistDialog?.dismiss()
        reminderEditorDialog?.dismiss()
        serviceScope.cancel()
        LockStateTracker.enforceHome = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val EXTRA_TEMP_LOCK_MINUTES = "extra_temp_lock_minutes"
    }

    private fun dialogWindowType(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }
    }

    private fun openReminderEditorFromOverlay(reminder: Reminder? = null) {
        mainHandler.post {
            reminderEditorDialog?.dismiss()
            reminderEditorDialog = showReminderEditorDialog(
                context = overlayContext,
                reminder = reminder,
                repository = reminderRepository,
                scope = serviceScope,
                overlayWindow = true,
                onDismiss = { reminderEditorDialog = null }
            )
        }
    }

    private fun showOverlayAlert(message: CharSequence) {
        val dialog = AlertDialog.Builder(overlayContext)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .create()
        dialog.window?.setType(dialogWindowType())
        dialog.show()
    }

    private fun completeReminderFromOverlay(reminder: Reminder) {
        if (reminder.isCompleted) return
        serviceScope.launch {
            reminderRepository.completeReminder(reminder)
        }
    }

    private fun LockSchedule.debugDescription(): String {
        return "id=$id ${rangeLabel()} days=[${dayLabels()}]"
    }
}
