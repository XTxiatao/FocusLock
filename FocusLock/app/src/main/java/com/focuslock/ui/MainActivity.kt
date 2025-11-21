package com.focuslock.ui

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.databinding.ActivityMainBinding
import com.focuslock.model.LockSchedule
import com.focuslock.service.LockOverlayService
import com.focuslock.util.PermissionHelper
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDateTime

private const val LOG_TAG = "FocusLockMain"

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var permissionDialog: AlertDialog? = null
    private val repository by lazy { (application as FocusLockApplication).lockScheduleRepository }
    private val adapter = ScheduleAdapter(::toggleSchedule, ::deleteSchedule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scheduleList.layoutManager = LinearLayoutManager(this)
        binding.scheduleList.adapter = adapter
        binding.addPlanButton.setOnClickListener { showAddPlanDialog() }
        binding.tempLockButton.setOnClickListener { showTemporaryLockDialog() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                repository.schedulesFlow.collect { adapter.submitList(it) }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                repository.enabledSchedulesFlow.collect { enabled ->
                    if (enabled.isNotEmpty()) {
                        binding.statusText.text = getString(R.string.service_notification_title)
                        binding.statusDescription.text = getString(R.string.service_notification_text)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        evaluatePermissions()
    }

    override fun onDestroy() {
        permissionDialog?.dismiss()
        super.onDestroy()
    }

    private fun evaluatePermissions() {
        val missing = PermissionHelper.getMissingPermissions(this)
        if (missing.isEmpty()) {
            binding.statusText.text = getString(R.string.service_notification_title)
            binding.statusDescription.text = getString(R.string.service_notification_text)
            binding.planContainer.visibility = View.VISIBLE
        } else {
            binding.planContainer.visibility = View.GONE
            binding.statusText.text = getString(R.string.permission_missing_title)
            binding.statusDescription.text = missing.first().descriptionText(this)
            promptForPermission(missing.first())
        }
    }

    private fun promptForPermission(permission: PermissionHelper.RequiredPermission) {
        if (permissionDialog?.isShowing == true) {
            return
        }

        val title = getString(R.string.permission_missing_title)
        val message = permission.descriptionText(this)
        val intent = permission.settingsIntent(this)

        permissionDialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.open_settings_button) { _, _ ->
                PermissionHelper.markPermissionAcknowledged(this, permission)
                intent?.let { startActivity(it) }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setOnDismissListener { permissionDialog = null }
            .show()
    }

    private fun saveLockPlan(startMinutes: Int, endMinutes: Int, mask: Int) {
        val schedule = LockSchedule(
            startMinutes = startMinutes,
            endMinutes = endMinutes,
            daysBitmask = mask,
            isEnabled = false
        )

        lifecycleScope.launch {
            repository.insert(schedule)
            Log.d(LOG_TAG, "Saved plan: ${schedule.describe()}")
        }
    }

    private fun toggleSchedule(schedule: LockSchedule) {
        val updated = schedule.copy(isEnabled = !schedule.isEnabled)
        lifecycleScope.launch {
            repository.update(updated)
            if (updated.isEnabled) {
                startLockService()
            } else {
                stopServiceIfNoActivePlans()
            }
            val currentTime = LocalDateTime.now()
            val currentLabel = String.format("%02d:%02d", currentTime.hour, currentTime.minute)
            Log.d(
                LOG_TAG,
                "Plan ${updated.id} now ${if (updated.isEnabled) "enabled" else "disabled"}, current time $currentLabel, matches? ${updated.matches(currentTime)}"
            )
        }
    }

    private fun startLockService() {
        val intent = Intent(this, LockOverlayService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    private fun stopServiceIfNoActivePlans() {
        lifecycleScope.launch {
            val hasActive = repository.enabledSchedulesFlow.firstOrNull()?.isNotEmpty() == true
            if (!hasActive) {
                stopService(Intent(this@MainActivity, LockOverlayService::class.java))
            }
        }
    }

    private fun deleteSchedule(schedule: LockSchedule) {
        lifecycleScope.launch {
            repository.delete(schedule)
            Toast.makeText(this@MainActivity, "Plan deleted", Toast.LENGTH_SHORT).show()
            stopServiceIfNoActivePlans()
        }
    }

    private fun showTemporaryLockDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_temporary_lock, null)
        val picker = dialogView.findViewById<NumberPicker>(R.id.durationPicker)
        picker.minValue = 5
        picker.maxValue = 120
        picker.value = 30

        AlertDialog.Builder(this)
            .setTitle(R.string.temporary_lock_title)
            .setView(dialogView)
            .setPositiveButton(R.string.temporary_lock_dialog_confirm) { _, _ ->
                startTemporaryLock(picker.value)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun startTemporaryLock(durationMinutes: Int) {
        val intent = Intent(this, LockOverlayService::class.java).apply {
            putExtra(LockOverlayService.EXTRA_TEMP_LOCK_MINUTES, durationMinutes)
        }
        ContextCompat.startForegroundService(this, intent)
        Log.d(LOG_TAG, "Starting temporary lock for $durationMinutes minutes")
    }

    private fun showAddPlanDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_plan, null)
        val startButton = dialogView.findViewById<Button>(R.id.dialogStartTimeButton)
        val endButton = dialogView.findViewById<Button>(R.id.dialogEndTimeButton)
        val startValue = dialogView.findViewById<TextView>(R.id.dialogStartTimeValue)
        val endValue = dialogView.findViewById<TextView>(R.id.dialogEndTimeValue)

        var startMinutesLocal = 22 * 60
        var endMinutesLocal = 23 * 60
        startValue.text = formatTimeLabel(startMinutesLocal)
        endValue.text = formatTimeLabel(endMinutesLocal)

        val selectedDays = mutableSetOf<DayOfWeek>()
        val chips = mapOf(
            dialogView.findViewById<Chip>(R.id.dialogChipMonday) to DayOfWeek.MONDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipTuesday) to DayOfWeek.TUESDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipWednesday) to DayOfWeek.WEDNESDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipThursday) to DayOfWeek.THURSDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipFriday) to DayOfWeek.FRIDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipSaturday) to DayOfWeek.SATURDAY,
            dialogView.findViewById<Chip>(R.id.dialogChipSunday) to DayOfWeek.SUNDAY
        )

        chips.forEach { (chip, day) ->
            chip.isChecked = true
            selectedDays.add(day)
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedDays.add(day)
                } else {
                    selectedDays.remove(day)
                }
            }
        }

        startButton.setOnClickListener {
            showTimePickerDialog(startMinutesLocal) {
                startMinutesLocal = it
                startValue.text = formatTimeLabel(it)
            }
        }

        endButton.setOnClickListener {
            showTimePickerDialog(endMinutesLocal) {
                endMinutesLocal = it
                endValue.text = formatTimeLabel(it)
            }
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.add_plan_button)
            .setView(dialogView)
            .setPositiveButton(R.string.save_plan_button) { _, _ ->
                if (selectedDays.isEmpty()) {
                    Toast.makeText(this, "Select at least one day", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val mask = selectedDays.fold(0) { acc, day -> acc or (1 shl day.ordinal) }
                saveLockPlan(startMinutesLocal, endMinutesLocal, mask)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showTimePickerDialog(initialMinutes: Int, onSelected: (Int) -> Unit) {
        val hour = initialMinutes / 60
        val minute = initialMinutes % 60
        TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                onSelected(selectedHour * 60 + selectedMinute)
            },
            hour,
            minute,
            true
        ).show()
    }

    private fun formatTimeLabel(minutes: Int): String {
        val hour = minutes / 60
        val minute = minutes % 60
        return String.format("%02d:%02d", hour, minute)
    }
}

private fun PermissionHelper.RequiredPermission.descriptionText(activity: ComponentActivity): String {
    return when (this) {
        PermissionHelper.RequiredPermission.OVERLAY -> activity.getString(R.string.permission_missing_message) +
            " Overlay access is needed for floating window."
        PermissionHelper.RequiredPermission.USAGE_STATS -> activity.getString(R.string.permission_missing_message) +
            " Usage data keeps the lock in sync."
        PermissionHelper.RequiredPermission.ACCESSIBILITY -> activity.getString(R.string.permission_missing_message) +
            " Accessibility access powers the input interception."
        PermissionHelper.RequiredPermission.BATTERY -> activity.getString(R.string.permission_missing_message) +
            " Background optimization must be ignored."
        PermissionHelper.RequiredPermission.AUTO_START -> activity.getString(R.string.permission_missing_message) +
            " Allow auto start to keep the service running after boot."
    }
}

private fun LockSchedule.describe(): String {
    return "Lock plan id=$id ${rangeLabel()} days=[${dayLabels()}]"
}
