package com.focuslock.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.databinding.OverlayViewBinding
import com.focuslock.model.LockSchedule
import com.focuslock.util.LockStateTracker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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
    private var enabledSchedules = emptyList<LockSchedule>()
    private lateinit var windowParams: WindowManager.LayoutParams
    private var foregroundStarted = false
    private var temporaryLockExpiryMillis = 0L
    private var evaluationRunning = false
    private var nextUnlockTimeMillis = 0L
    private var currentSchedule: LockSchedule? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_LOG_TAG, "onCreate")
        promoteToForeground()
        repository = (application as FocusLockApplication).lockScheduleRepository
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        binding = OverlayViewBinding.inflate(LayoutInflater.from(this))
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
    }

    private fun prepareOverlay() {
        binding.root.setOnTouchListener { _, _ -> true }
        binding.forceUnlockButton.setOnClickListener {
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
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
        }
    }

    private fun evaluateSchedule() {
        val tempActive = isTemporaryLockActive()
        if (!tempActive && enabledSchedules.isEmpty()) {
            Log.d(
                SERVICE_LOG_TAG,
                "No active schedules or temporary lock; keeping service alive without overlay"
            )
            hideOverlay()
            stopCountdown()
            LockStateTracker.enforceHome = false
            return
        }

        val now = LocalDateTime.now()
        val activeSchedule = enabledSchedules.firstOrNull { it.matches(now) }
        val scheduleActive = activeSchedule != null
        val overlayState = if (overlayAdded) "visible" else "hidden"
        val timeLabel = String.format("%02d:%02d:%02d", now.hour, now.minute, now.second)
        Log.d(
            SERVICE_LOG_TAG,
            "Evaluating now=$timeLabel tempActive=$tempActive scheduleActive=$scheduleActive overlay=$overlayState enabledCount=${enabledSchedules.size}"
        )
        currentSchedule = activeSchedule
        val shouldLock = tempActive || scheduleActive
        LockStateTracker.enforceHome = shouldLock
        if (shouldLock) {
            showOverlay()
            val targetMillis = when {
                tempActive -> temporaryLockExpiryMillis
                activeSchedule != null -> planEndMillis(activeSchedule, now)
                else -> System.currentTimeMillis()
            }
            startCountdownFor(targetMillis)
        } else {
            hideOverlay()
            stopCountdown()
        }
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

    private fun planEndMillis(schedule: LockSchedule, now: LocalDateTime): Long {
        val nextEndDay = if (schedule.startMinutes <= schedule.endMinutes) {
            now
        } else {
            val currentMinute = now.hour * 60 + now.minute
            if (currentMinute >= schedule.startMinutes) {
                now.plusDays(1)
            } else {
                now
            }
        }
        val endHour = schedule.endMinutes / 60
        val endMinute = schedule.endMinutes % 60
        val endDateTime = nextEndDay
            .withHour(endHour)
            .withMinute(endMinute)
            .withSecond(0)
            .withNano(0)
        return endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
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
        serviceScope.cancel()
        LockStateTracker.enforceHome = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val EXTRA_TEMP_LOCK_MINUTES = "extra_temp_lock_minutes"
    }

    private fun LockSchedule.debugDescription(): String {
        return "id=$id ${rangeLabel()} days=[${dayLabels()}]"
    }
}
