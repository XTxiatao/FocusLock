package com.focuslock.ui

import android.app.AlertDialog
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
    private val adapter = ScheduleAdapter(::toggleSchedule, ::confirmDeleteSchedule, ::requestEditSchedule)

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
        val hasAll = missing.isEmpty()
        binding.planContainer.visibility = if (hasAll) View.VISIBLE else View.GONE
        binding.tempLockContainer.visibility = if (hasAll) View.VISIBLE else View.GONE
        if (!hasAll) {
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

    private fun saveLockPlan(startMinutes: Int, endMinutes: Int, mask: Int, existing: LockSchedule?) {
        val schedule = if (existing == null) {
            LockSchedule(
                startMinutes = startMinutes,
                endMinutes = endMinutes,
                daysBitmask = mask,
                isEnabled = false
            )
        } else {
            existing.copy(
                startMinutes = startMinutes,
                endMinutes = endMinutes,
                daysBitmask = mask,
                isEnabled = false
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            if (existing == null) {
                repository.insert(schedule)
            } else {
                repository.update(schedule)
            }
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

    private fun confirmDeleteSchedule(schedule: LockSchedule) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.confirm_delete_plan)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                deleteSchedule(schedule)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun requestEditSchedule(schedule: LockSchedule) {
        if (schedule.isEnabled) {
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.disable_plan_to_edit)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        } else {
            showAddPlanDialog(schedule)
        }
    }

    private fun showTemporaryLockDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_temporary_lock, null)
        val hourPicker = dialogView.findViewById<NumberPicker>(R.id.durationHourPicker).apply {
            minValue = 0
            maxValue = 24
            value = 0
            setFormatter { String.format("%02d", it) }
        }
        val minutePicker = dialogView.findViewById<NumberPicker>(R.id.durationMinutePicker).apply {
            minValue = 0
            maxValue = 59
            value = 30
            setFormatter { String.format("%02d", it) }
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.temporary_lock_title)
            .setView(dialogView)
            .setPositiveButton(R.string.temporary_lock_dialog_confirm, null)
            .setNegativeButton(android.R.string.cancel, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val totalMinutes = hourPicker.value * 60 + minutePicker.value
                if (totalMinutes <= 0) {
                    Toast.makeText(requireContext(), R.string.temporary_lock_duration_error, Toast.LENGTH_SHORT).show()
                } else {
                    startTemporaryLock(totalMinutes)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

    private fun startTemporaryLock(durationMinutes: Int) {
        val intent = Intent(requireContext(), LockOverlayService::class.java).apply {
            putExtra(LockOverlayService.EXTRA_TEMP_LOCK_MINUTES, durationMinutes)
        }
        ContextCompat.startForegroundService(requireContext(), intent)
        Log.d(LOG_TAG, "Starting temporary lock for $durationMinutes minutes")
    }

    private fun showAddPlanDialog(existing: LockSchedule? = null) {
        val dialogBinding = DialogAddPlanBinding.inflate(layoutInflater)
        var startMinutesLocal = existing?.startMinutes ?: 22 * 60
        var endMinutesLocal = existing?.endMinutes ?: 23 * 60

        dialogBinding.startHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = startMinutesLocal / 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.startMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = startMinutesLocal % 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.endHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = endMinutesLocal / 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.endMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = endMinutesLocal % 60
            setFormatter { String.format("%02d", it) }
        }

        val selectedDays = existing?.let { schedule ->
            DayOfWeek.values().filter { schedule.isDaySelected(it) }.toMutableSet()
        } ?: mutableSetOf<DayOfWeek>().apply { addAll(DayOfWeek.values()) }
        val chips = mapOf(
            dialogBinding.dialogChipMonday to DayOfWeek.MONDAY,
            dialogBinding.dialogChipTuesday to DayOfWeek.TUESDAY,
            dialogBinding.dialogChipWednesday to DayOfWeek.WEDNESDAY,
            dialogBinding.dialogChipThursday to DayOfWeek.THURSDAY,
            dialogBinding.dialogChipFriday to DayOfWeek.FRIDAY,
            dialogBinding.dialogChipSaturday to DayOfWeek.SATURDAY,
            dialogBinding.dialogChipSunday to DayOfWeek.SUNDAY
        )

        val selectAllByDefault = existing == null
        chips.forEach { (chip, day) ->
            val checked = if (selectAllByDefault) true else selectedDays.contains(day)
            chip.isChecked = checked
            if (selectAllByDefault && !selectedDays.contains(day)) {
                selectedDays.add(day)
            }
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedDays.add(day) else selectedDays.remove(day)
            }
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (existing == null) R.string.add_plan_button else R.string.edit_plan_button)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save_plan_button) { _, _ ->
                startMinutesLocal = dialogBinding.startHourPicker.value * 60 + dialogBinding.startMinutePicker.value
                endMinutesLocal = dialogBinding.endHourPicker.value * 60 + dialogBinding.endMinutePicker.value
                if (selectedDays.isEmpty()) {
                    Toast.makeText(requireContext(), "Select at least one day", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val mask = selectedDays.fold(0) { acc, day -> acc or (1 shl day.ordinal) }
                saveLockPlan(startMinutesLocal, endMinutesLocal, mask, existing)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
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
