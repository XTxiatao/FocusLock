package com.focuslock.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.data.ReminderRepository
import com.focuslock.databinding.DialogArchivedRemindersBinding
import com.focuslock.databinding.DialogReminderEditorBinding
import com.focuslock.databinding.FragmentReminderBinding
import com.focuslock.model.Reminder
import com.focuslock.model.ReminderRecurrence
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.launch

class ReminderFragment : Fragment() {

    private var _binding: FragmentReminderBinding? = null
    private val binding get() = _binding!!

    private val reminderRepository: ReminderRepository by lazy {
        (requireActivity().application as FocusLockApplication).reminderRepository
    }

    private val reminderAdapter by lazy {
        ReminderAdapter(::toggleReminder, ::completeReminder, ::archiveReminder, ::showReminderEditor)
    }
    private val archivedAdapter by lazy {
        ArchivedReminderAdapter(::restoreReminder, ::confirmDeleteReminder, ::showReminderEditor)
    }

    private var archivedDialog: AlertDialog? = null
    private var archivedDialogBinding: DialogArchivedRemindersBinding? = null
    private var latestReminders: List<Reminder> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.reminderRecyclerView.adapter = reminderAdapter
        binding.archivedCard.setOnClickListener { showArchivedDialog() }
        binding.addReminderFab.setOnClickListener { showReminderEditor() }
        binding.archivedSubtitle.text = resources.getQuantityString(R.plurals.reminder_archived_count, 0, 0)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reminderRepository.remindersFlow.collect { reminders ->
                    latestReminders = reminders
                    val archived = reminders.filter { it.isArchived }.orderByActivation()
                    val nonArchived = reminders.filterNot { it.isArchived }
                    val orderedActive = nonArchived.orderByActivation()
                    reminderAdapter.submitList(orderedActive)
                    archivedAdapter.submitList(archived)
                    binding.reminderRecyclerView.isVisible = orderedActive.isNotEmpty()
                    binding.emptyRemindersView.isVisible = orderedActive.isEmpty()
                    val subtitle = resources.getQuantityString(
                        R.plurals.reminder_archived_count,
                        archived.size,
                        archived.size
                    )
                    binding.archivedSubtitle.text = subtitle
                    archivedDialogBinding?.let { dialogBinding ->
                        dialogBinding.archivedEmptyText.isVisible = archived.isEmpty()
                    }
                }
            }
        }
    }

    private fun toggleReminder(reminder: Reminder) {
        viewLifecycleOwner.lifecycleScope.launch {
            val now = System.currentTimeMillis()
            val updated = reminder.copy(
                isActive = !reminder.isActive,
                updatedAtMillis = now
            )
            reminderRepository.updateReminder(updated)
        }
    }

    private fun completeReminder(reminder: Reminder) {
        if (reminder.isRepeating) {
            Toast.makeText(requireContext(), R.string.reminder_completed_blocked, Toast.LENGTH_SHORT).show()
            return
        }
        viewLifecycleOwner.lifecycleScope.launch {
            val now = System.currentTimeMillis()
            reminderRepository.updateReminder(
                reminder.copy(
                    isCompleted = true,
                    isActive = false,
                    isArchived = true,
                    updatedAtMillis = now
                )
            )
        }
    }

    private fun archiveReminder(reminder: Reminder) {
        if (!reminder.isCompleted && reminder.isActive) {
            Toast.makeText(requireContext(), R.string.reminder_archive_blocked, Toast.LENGTH_SHORT).show()
            return
        }
        viewLifecycleOwner.lifecycleScope.launch {
            val now = System.currentTimeMillis()
            reminderRepository.updateReminder(
                reminder.copy(
                    isArchived = true,
                    isActive = false,
                    updatedAtMillis = now
                )
            )
        }
    }

    private fun restoreReminder(reminder: Reminder) {
        if (reminder.isCompleted) {
            Toast.makeText(requireContext(), R.string.reminder_restore_blocked, Toast.LENGTH_SHORT).show()
            return
        }
        viewLifecycleOwner.lifecycleScope.launch {
            reminderRepository.updateReminder(
                reminder.copy(
                    isArchived = false,
                    updatedAtMillis = System.currentTimeMillis()
                )
            )
        }
    }

    private fun confirmDeleteReminder(reminder: Reminder) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.reminder_delete_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    reminderRepository.deleteReminder(reminder)
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showArchivedDialog() {
        val dialogBinding = DialogArchivedRemindersBinding.inflate(layoutInflater)
        dialogBinding.archivedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        dialogBinding.archivedRecyclerView.adapter = archivedAdapter
        val archived = latestReminders.filter { it.isArchived }.orderByActivation()
        dialogBinding.archivedEmptyText.isVisible = archived.isEmpty()
        archivedDialogBinding = dialogBinding
        archivedDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.reminder_archived_card_title)
            .setView(dialogBinding.root)
            .setPositiveButton(android.R.string.ok, null)
            .create().also { dialog ->
                dialog.setOnDismissListener {
                    archivedDialogBinding = null
                }
                dialog.show()
            }
    }

    private fun showReminderEditor(reminder: Reminder? = null) {
        val dialogBinding = DialogReminderEditorBinding.inflate(layoutInflater)
        dialogBinding.titleInput.setText(reminder?.title.orEmpty())
        dialogBinding.descriptionInput.setText(reminder?.description.orEmpty())

        val recurrenceOptions = listOf(
            ReminderRecurrence.NONE,
            ReminderRecurrence.DAILY,
            ReminderRecurrence.WEEKLY,
            ReminderRecurrence.MONTHLY,
            ReminderRecurrence.YEARLY
        )

        var selectedRecurrence = reminder?.recurrence ?: ReminderRecurrence.NONE
        val zone = ZoneId.systemDefault()
        var selectedDateTime = reminder?.anchorDateTime(zone)
            ?: ZonedDateTime.now(zone).plusMinutes(30).withSecond(0).withNano(0)
        val weeklySelection = reminder?.selectedDays()?.toMutableSet()
            ?: mutableSetOf(selectedDateTime.dayOfWeek)

        val dayChipMap = mapOf(
            DayOfWeek.MONDAY to dialogBinding.chipMonday,
            DayOfWeek.TUESDAY to dialogBinding.chipTuesday,
            DayOfWeek.WEDNESDAY to dialogBinding.chipWednesday,
            DayOfWeek.THURSDAY to dialogBinding.chipThursday,
            DayOfWeek.FRIDAY to dialogBinding.chipFriday,
            DayOfWeek.SATURDAY to dialogBinding.chipSaturday,
            DayOfWeek.SUNDAY to dialogBinding.chipSunday
        )
        fun refreshWeekChips() {
            dayChipMap.forEach { (day, chip) ->
                chip.setOnCheckedChangeListener(null)
                chip.isChecked = weeklySelection.contains(day)
                chip.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) weeklySelection.add(day) else weeklySelection.remove(day)
                }
            }
        }
        refreshWeekChips()

        fun updateDateLabel() {
            dialogBinding.dateButton.text = selectedDateTime.format(DATE_FORMATTER)
            dialogBinding.timeButton.text = selectedDateTime.format(TIME_FORMATTER)
        }
        updateDateLabel()

        dialogBinding.dateButton.setOnClickListener {
            val currentDate = selectedDateTime.toLocalDate()
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    selectedDateTime = selectedDateTime
                        .withDayOfMonth(1)
                        .withYear(year)
                        .withMonth(month + 1)
                        .withDayOfMonth(dayOfMonth)
                    updateDateLabel()
                },
                currentDate.year,
                currentDate.monthValue - 1,
                currentDate.dayOfMonth
            ).show()
        }

        dialogBinding.timeButton.setOnClickListener {
            val currentTime = selectedDateTime.toLocalTime()
            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    selectedDateTime = selectedDateTime.withHour(hourOfDay).withMinute(minute)
                    updateDateLabel()
                },
                currentTime.hour,
                currentTime.minute,
                true
            ).show()
        }

        dialogBinding.recurrenceSpinner.setSelection(recurrenceOptions.indexOf(selectedRecurrence))
        fun updateRecurrenceUi() {
            dialogBinding.weeklyContainer.isVisible = selectedRecurrence == ReminderRecurrence.WEEKLY
            dialogBinding.completedSwitch.isEnabled = !selectedRecurrence.isRepeating
            if (selectedRecurrence.isRepeating) {
                dialogBinding.completedSwitch.isChecked = false
            }
            if (selectedRecurrence != ReminderRecurrence.WEEKLY) {
                if (weeklySelection.isEmpty()) {
                    weeklySelection.add(selectedDateTime.dayOfWeek)
                }
            }
            refreshWeekChips()
        }
        updateRecurrenceUi()
        dialogBinding.recurrenceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedRecurrence = recurrenceOptions[position]
                updateRecurrenceUi()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

        dialogBinding.activeSwitch.isChecked = reminder?.isActive ?: true
        dialogBinding.completedSwitch.isChecked = reminder?.isCompleted ?: false
        var archivedState = reminder?.isArchived ?: false
        var suppressActiveSwitchChange = false
        fun resetActiveSwitch() {
            suppressActiveSwitchChange = true
            dialogBinding.activeSwitch.isChecked = false
            suppressActiveSwitchChange = false
        }

        dialogBinding.activeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (suppressActiveSwitchChange) {
                return@setOnCheckedChangeListener
            }
            if (reminder?.isArchived == true && isChecked && archivedState) {
                AlertDialog.Builder(requireContext())
                    .setMessage(R.string.reminder_unarchive_prompt)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        resetActiveSwitch()
                    }
                    .show()
            }
        }

        val dialogTitle = if (reminder == null) {
            getString(R.string.reminder_add_button)
        } else {
            getString(R.string.reminder_edit_title)
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(dialogTitle)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.reminder_save_button, null)
            .setNegativeButton(R.string.reminder_cancel_button, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val title = dialogBinding.titleInput.text?.toString()?.trim().orEmpty()
                if (title.isBlank()) {
                    Toast.makeText(requireContext(), R.string.reminder_title_required, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val weeklyMask = if (selectedRecurrence == ReminderRecurrence.WEEKLY) {
                    if (weeklySelection.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.reminder_weekly_required, Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    encodeWeekDays(weeklySelection)
                } else {
                    0
                }
                viewLifecycleOwner.lifecycleScope.launch {
                    val unique = reminderRepository.ensureUniqueTitle(title, reminder?.id)
                    if (!unique) {
                        Toast.makeText(requireContext(), R.string.reminder_duplicate_title, Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    val now = System.currentTimeMillis()
                    val completedChecked = (!selectedRecurrence.isRepeating) && dialogBinding.completedSwitch.isChecked
                    val activeChecked = dialogBinding.activeSwitch.isChecked
                    val shouldArchive = when {
                        completedChecked -> true
                        archivedState -> true
                        else -> false
                    }
                    val updatedReminder = Reminder(
                        id = reminder?.id ?: 0,
                        title = title,
                        description = dialogBinding.descriptionInput.text?.toString().orEmpty(),
                        anchorDateTimeMillis = selectedDateTime.withSecond(0).withNano(0).toInstant().toEpochMilli(),
                        recurrence = selectedRecurrence,
                        weeklyDaysMask = weeklyMask,
                        isActive = if (shouldArchive) false else activeChecked,
                        isCompleted = completedChecked,
                        isArchived = shouldArchive,
                        createdAtMillis = reminder?.createdAtMillis ?: now,
                        updatedAtMillis = now
                    )
                    if (reminder == null) {
                        reminderRepository.addReminder(updatedReminder)
                    } else {
                        reminderRepository.updateReminder(updatedReminder)
                    }
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

    private fun encodeWeekDays(days: Set<DayOfWeek>): Int {
        var mask = 0
        days.forEach { day ->
            mask = mask or (1 shl day.ordinal)
        }
        return mask
    }

    private fun List<Reminder>.orderByActivation(): List<Reminder> {
        val active = filter { it.isActive }
        val inactive = filterNot { it.isActive }
        return active + inactive
    }

    override fun onDestroyView() {
        archivedDialog?.dismiss()
        archivedDialogBinding = null
        archivedDialog = null
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
        private val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}
