package com.focuslock.ui

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.databinding.DialogAddPlanBinding
import com.focuslock.databinding.FragmentDeviceLockBinding
import com.focuslock.model.LockSchedule
import com.focuslock.service.LockOverlayService
import com.focuslock.util.PermissionHelper
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDateTime

private const val LOG_TAG = "DeviceLockFragment"

class DeviceLockFragment : Fragment() {

    private var _binding: FragmentDeviceLockBinding? = null
    private val binding get() = _binding!!

    private val repository by lazy {
        (requireActivity().application as FocusLockApplication).lockScheduleRepository
    }
    private val adapter = ScheduleAdapter(::toggleSchedule, ::deleteSchedule)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scheduleList.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleList.adapter = adapter
        binding.addPlanButton.setOnClickListener { showAddPlanDialog() }
        binding.tempLockButton.setOnClickListener { showTemporaryLockDialog() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repository.schedulesFlow.collect { adapter.submitList(it) }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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

    override fun onDestroyView() {
        permissionDialog?.dismiss()
        _binding = null
        super.onDestroyView()
    }

    private var permissionDialog: AlertDialog? = null

    private fun evaluatePermissions() {
        val missing = PermissionHelper.getMissingPermissions(requireContext())
        if (missing.isEmpty()) {
            binding.statusText.text = getString(R.string.service_notification_title)
            binding.statusDescription.text = getString(R.string.service_notification_text)
            binding.planContainer.visibility = View.VISIBLE
        } else {
            binding.planContainer.visibility = View.GONE
            binding.statusText.text = getString(R.string.permission_missing_title)
            binding.statusDescription.text = missing.first().descriptionText(requireActivity())
            promptForPermission(missing.first())
        }
    }

    private fun promptForPermission(permission: PermissionHelper.RequiredPermission) {
        if (permissionDialog?.isShowing == true) {
            return
        }

        val title = getString(R.string.permission_missing_title)
        val message = permission.descriptionText(requireActivity())
        val intent = permission.settingsIntent(requireContext())

        permissionDialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.open_settings_button) { _, _ ->
                PermissionHelper.markPermissionAcknowledged(requireContext(), permission)
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

        viewLifecycleOwner.lifecycleScope.launch {
            repository.insert(schedule)
            Log.d(LOG_TAG, "Saved plan: ${schedule.describe()}")
        }
    }

    private fun toggleSchedule(schedule: LockSchedule) {
        val updated = schedule.copy(isEnabled = !schedule.isEnabled)
        viewLifecycleOwner.lifecycleScope.launch {
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
        val intent = Intent(requireContext(), LockOverlayService::class.java)
        ContextCompat.startForegroundService(requireContext(), intent)
    }

    private fun stopServiceIfNoActivePlans() {
        viewLifecycleOwner.lifecycleScope.launch {
            val hasActive = repository.enabledSchedulesFlow.firstOrNull()?.isNotEmpty() == true
            if (!hasActive) {
                requireContext().stopService(Intent(requireContext(), LockOverlayService::class.java))
            }
        }
    }

    private fun deleteSchedule(schedule: LockSchedule) {
        viewLifecycleOwner.lifecycleScope.launch {
            repository.delete(schedule)
            Toast.makeText(requireContext(), "Plan deleted", Toast.LENGTH_SHORT).show()
            stopServiceIfNoActivePlans()
        }
    }

    private fun showTemporaryLockDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_temporary_lock, null)
        val picker = dialogView.findViewById<NumberPicker>(R.id.durationPicker)
        picker.minValue = 5
        picker.maxValue = 120
        picker.value = 30

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.temporary_lock_title)
            .setView(dialogView)
            .setPositiveButton(R.string.temporary_lock_dialog_confirm) { _, _ ->
                startTemporaryLock(picker.value)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun startTemporaryLock(durationMinutes: Int) {
        val intent = Intent(requireContext(), LockOverlayService::class.java).apply {
            putExtra(LockOverlayService.EXTRA_TEMP_LOCK_MINUTES, durationMinutes)
        }
        ContextCompat.startForegroundService(requireContext(), intent)
        Log.d(LOG_TAG, "Starting temporary lock for $durationMinutes minutes")
    }

    private fun showAddPlanDialog() {
        val dialogBinding = DialogAddPlanBinding.inflate(layoutInflater)
        var startMinutesLocal = 22 * 60
        var endMinutesLocal = 23 * 60
        dialogBinding.dialogStartTimeValue.text = formatTimeLabel(startMinutesLocal)
        dialogBinding.dialogEndTimeValue.text = formatTimeLabel(endMinutesLocal)

        val selectedDays = mutableSetOf<DayOfWeek>()
        val chips = mapOf(
            dialogBinding.dialogChipMonday to DayOfWeek.MONDAY,
            dialogBinding.dialogChipTuesday to DayOfWeek.TUESDAY,
            dialogBinding.dialogChipWednesday to DayOfWeek.WEDNESDAY,
            dialogBinding.dialogChipThursday to DayOfWeek.THURSDAY,
            dialogBinding.dialogChipFriday to DayOfWeek.FRIDAY,
            dialogBinding.dialogChipSaturday to DayOfWeek.SATURDAY,
            dialogBinding.dialogChipSunday to DayOfWeek.SUNDAY
        )

        chips.forEach { (chip, day) ->
            chip.isChecked = true
            selectedDays.add(day)
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedDays.add(day) else selectedDays.remove(day)
            }
        }

        dialogBinding.dialogStartTimeButton.setOnClickListener {
            showTimePickerDialog(startMinutesLocal) {
                startMinutesLocal = it
                dialogBinding.dialogStartTimeValue.text = formatTimeLabel(it)
            }
        }
        dialogBinding.dialogEndTimeButton.setOnClickListener {
            showTimePickerDialog(endMinutesLocal) {
                endMinutesLocal = it
                dialogBinding.dialogEndTimeValue.text = formatTimeLabel(it)
            }
        }

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_plan_button)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save_plan_button) { _, _ ->
                if (selectedDays.isEmpty()) {
                    Toast.makeText(requireContext(), "Select at least one day", Toast.LENGTH_SHORT).show()
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
            requireContext(),
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
