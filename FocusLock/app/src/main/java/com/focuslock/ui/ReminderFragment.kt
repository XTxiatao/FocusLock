package com.focuslock.ui

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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
import com.focuslock.databinding.DialogTimePickerBinding
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

    private val dayAdapter by lazy {
        ReminderDayAdapter(::showReminderEditor, ::completeReminderFromPill)
    }
    private val completedAdapter by lazy {
        CompletedDayAdapter(::showReminderEditor, ::attachSwipeToCompletedRecycler)
    }

    private var latestReminders: List<Reminder> = emptyList()
    private var archivedDialog: AlertDialog? = null
    private var archivedDialogBinding: DialogArchivedRemindersBinding? = null

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
        binding.reminderRecyclerView.adapter = dayAdapter
        binding.archivedCard.setOnClickListener { showArchivedDialog() }
        binding.addReminderFab.setOnClickListener { showReminderEditor() }
        binding.archivedSubtitle.text =
            resources.getQuantityString(R.plurals.reminder_archived_count, 0, 0)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reminderRepository.remindersFlow.collect { reminders ->
                    latestReminders = reminders
                    val (timedReminders, floatingReminders) = reminders
                        .filter { !it.isCompleted }
                        .partition { it.anchorDateTimeMillis != null }
                    val activeGroups = buildDayGroups(timedReminders)
                    val floatingGroups = buildFloatingGroup(floatingReminders)
                    dayAdapter.submitList(activeGroups + floatingGroups)
                    binding.reminderRecyclerView.isVisible = (activeGroups + floatingGroups).isNotEmpty()
                    binding.emptyRemindersView.isVisible = (activeGroups + floatingGroups).isEmpty()

                    val completedList = reminders.filter { it.isCompleted }
                    val completedFloating = completedList.filter { it.anchorDateTimeMillis == null }
                    val completedWithDates = completedList.filter { it.anchorDateTimeMillis != null }
                    val completedGroups = buildCompletedGroups(completedWithDates) + buildCompletedFloatingGroup(completedFloating)
                    completedAdapter.submitList(completedGroups)
                    val subtitle = resources.getQuantityString(
                        R.plurals.reminder_archived_count,
                        completedList.size,
                        completedList.size
                    )
                    binding.archivedSubtitle.text = subtitle
                    archivedDialogBinding?.let { dialogBinding ->
                        dialogBinding.archivedEmptyText.isVisible = completedGroups.isEmpty()
                    }
                }
            }
        }
    }

    private fun buildDayGroups(reminders: List<Reminder>): List<ReminderDayGroup> {
        val zone = ZoneId.systemDefault()
        return reminders
            .filter { it.anchorDateTimeMillis != null }
            .groupBy { java.time.Instant.ofEpochMilli(it.anchorDateTimeMillis!!).atZone(zone).toLocalDate() }
            .toSortedMap()
            .map { (date, list) ->
                val startOfDay = date.atStartOfDay(zone).toInstant().toEpochMilli()
                ReminderDayGroup(
                    dateMillis = startOfDay,
                    reminders = list.sortedBy { it.anchorDateTimeMillis }
                )
            }
    }

    private fun buildFloatingGroup(reminders: List<Reminder>): List<ReminderDayGroup> =
        reminders.takeIf { it.isNotEmpty() }?.let {
            listOf(
                ReminderDayGroup(
                    dateMillis = Long.MAX_VALUE,
                    reminders = it.sortedBy { reminder -> reminder.title }
                )
            )
        } ?: emptyList()

    private fun buildCompletedGroups(reminders: List<Reminder>): List<CompletedDayGroup> {
        val zone = ZoneId.systemDefault()
        return reminders
            .filter { it.anchorDateTimeMillis != null }
            .groupBy { java.time.Instant.ofEpochMilli(it.anchorDateTimeMillis!!).atZone(zone).toLocalDate() }
            .toSortedMap()
            .map { (date, list) ->
                val startOfDay = date.atStartOfDay(zone).toInstant().toEpochMilli()
                CompletedDayGroup(
                    dateMillis = startOfDay,
                    reminders = list.sortedBy { it.anchorDateTimeMillis }
                )
            }
    }

    private fun buildCompletedFloatingGroup(reminders: List<Reminder>): List<CompletedDayGroup> {
        return reminders.takeIf { it.isNotEmpty() }?.let {
            listOf(
                CompletedDayGroup(
                    dateMillis = Long.MAX_VALUE,
                    reminders = it.sortedBy { reminder -> reminder.title }
                )
            )
        } ?: emptyList()
    }

    private fun completeReminderFromPill(reminder: Reminder) {
        if (reminder.isCompleted) return
        viewLifecycleOwner.lifecycleScope.launch {
            if (!reminder.isRepeating) {
                reminderRepository.updateReminder(reminder.copy(isCompleted = true))
            } else {
                val nextAnchor = reminder.nextCycleAnchorMillis()
                if (nextAnchor == null) {
                    reminderRepository.updateReminder(reminder.copy(isCompleted = true))
                } else {
                    val endReached = reminder.endDateTimeMillis?.let { nextAnchor > it } ?: false
                    val updated = if (endReached) {
                        reminder.copy(
                            anchorDateTimeMillis = nextAnchor,
                            isCompleted = true
                        )
                    } else {
                        reminder.copy(
                            anchorDateTimeMillis = nextAnchor
                        )
                    }
                    reminderRepository.updateReminder(updated)
                }
            }
        }
    }

    private fun showArchivedDialog() {
        val dialogBinding = DialogArchivedRemindersBinding.inflate(layoutInflater)
        dialogBinding.archivedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        dialogBinding.archivedRecyclerView.adapter = completedAdapter
        dialogBinding.archivedEmptyText.isVisible = completedAdapter.currentList.isEmpty()
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

        val zone = ZoneId.systemDefault()
        var selectedDueDateTime = reminder?.anchorDateTime(zone)?.withSecond(0)?.withNano(0)
        var selectedRecurrence = reminder?.recurrence ?: ReminderRecurrence.NONE
        if (selectedDueDateTime == null) {
            selectedRecurrence = ReminderRecurrence.NONE
        }
        var selectedEndDateTime = reminder?.endDateTimeMillis?.let {
            java.time.Instant.ofEpochMilli(it).atZone(zone).withSecond(0).withNano(0)
        }
        val weeklySelection = reminder?.selectedDays()?.toMutableSet()
            ?: mutableSetOf((selectedDueDateTime ?: ZonedDateTime.now(zone)).dayOfWeek)
        var dueEnabled = selectedDueDateTime != null
        var endEnabled = selectedEndDateTime != null
        var lastRecurrenceSelection = if (selectedRecurrence == ReminderRecurrence.NONE) {
            ReminderRecurrence.DAILY
        } else {
            selectedRecurrence
        }

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

        fun ensureDueDateTime(): ZonedDateTime =
            (selectedDueDateTime ?: ZonedDateTime.now(zone).plusMinutes(30)).withSecond(0).withNano(0)

        fun ensureEndDateTime(): ZonedDateTime =
            (selectedEndDateTime ?: selectedDueDateTime ?: ZonedDateTime.now(zone).plusMinutes(30))
                .withSecond(0)
                .withNano(0)

        fun updateToggleButton(button: Button, enabledState: Boolean) {
            val color = if (enabledState) {
                ContextCompat.getColor(requireContext(), R.color.plan_active_green)
            } else {
                ContextCompat.getColor(requireContext(), R.color.plan_inactive_gray)
            }
            button.backgroundTintList = ColorStateList.valueOf(color)
            button.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        }

        fun updateRecurrenceAvailability() {
            dialogBinding.recurrenceSpinner.isEnabled = dueEnabled
            dialogBinding.recurrenceSpinner.alpha = if (dueEnabled) 1f else 0.5f
        }

        fun updateDueUi() {
            updateToggleButton(dialogBinding.dueToggleButton, dueEnabled)
            dialogBinding.dueDateTimeButton.isEnabled = dueEnabled
            dialogBinding.dueDateTimeButton.text = selectedDueDateTime?.let {
                DATE_TIME_SUMMARY_FORMATTER.format(it)
            } ?: getString(R.string.reminder_no_due_time)
        }

        fun updateEndUi() {
            val active = dueEnabled && endEnabled
            dialogBinding.endToggleButton.isEnabled = dueEnabled
            updateToggleButton(dialogBinding.endToggleButton, active)
            dialogBinding.endDateTimeButton.isEnabled = active
            dialogBinding.endDateTimeButton.text = selectedEndDateTime?.let {
                DATE_TIME_SUMMARY_FORMATTER.format(it)
            } ?: getString(R.string.reminder_no_end_time)
        }

        fun updateRecurrenceUi() {
            dialogBinding.weeklyContainer.isVisible = dueEnabled && selectedRecurrence == ReminderRecurrence.WEEKLY
            if (selectedRecurrence != ReminderRecurrence.WEEKLY && dueEnabled) {
                if (weeklySelection.isEmpty()) {
                    weeklySelection.add((selectedDueDateTime ?: ZonedDateTime.now(zone)).dayOfWeek)
                }
            }
            refreshWeekChips()
        }

        dialogBinding.dueToggleButton.setOnClickListener {
            dueEnabled = !dueEnabled
            if (!dueEnabled) {
                selectedDueDateTime = null
                if (selectedRecurrence != ReminderRecurrence.NONE) {
                    lastRecurrenceSelection = selectedRecurrence
                }
                selectedRecurrence = ReminderRecurrence.NONE
                dialogBinding.recurrenceSpinner.setSelection(recurrenceOptions.indexOf(ReminderRecurrence.NONE))
                if (endEnabled) {
                    endEnabled = false
                    selectedEndDateTime = null
                }
            } else if (selectedDueDateTime == null) {
                selectedDueDateTime = ensureDueDateTime()
                selectedRecurrence = lastRecurrenceSelection
                dialogBinding.recurrenceSpinner.setSelection(recurrenceOptions.indexOf(selectedRecurrence))
            }
            updateRecurrenceAvailability()
            updateRecurrenceUi()
            updateDueUi()
            updateEndUi()
        }

        dialogBinding.dueDateTimeButton.setOnClickListener {
            pickDateTime(ensureDueDateTime()) { picked ->
                selectedDueDateTime = picked
                updateDueUi()
            }
        }

        dialogBinding.endToggleButton.setOnClickListener {
            endEnabled = !endEnabled
            if (!endEnabled) {
                selectedEndDateTime = null
            } else if (selectedEndDateTime == null) {
                selectedEndDateTime = ensureEndDateTime()
            }
            updateEndUi()
        }

        dialogBinding.endDateTimeButton.setOnClickListener {
            pickDateTime(ensureEndDateTime()) { picked ->
                selectedEndDateTime = picked
                updateEndUi()
            }
        }

        updateDueUi()
        updateEndUi()
        dialogBinding.recurrenceSpinner.setSelection(recurrenceOptions.indexOf(selectedRecurrence))
        updateRecurrenceAvailability()
        updateRecurrenceUi()
        dialogBinding.recurrenceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedRecurrence = recurrenceOptions[position]
                 if (selectedRecurrence != ReminderRecurrence.NONE) {
                    lastRecurrenceSelection = selectedRecurrence
                }
                updateRecurrenceUi()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
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
                val endMillis = selectedEndDateTime?.toInstant()?.toEpochMilli()
                viewLifecycleOwner.lifecycleScope.launch {
                    val unique = reminderRepository.ensureUniqueTitle(title, reminder?.id)
                    if (!unique) {
                        Toast.makeText(requireContext(), R.string.reminder_duplicate_title, Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    val updatedReminder = Reminder(
                        id = reminder?.id ?: 0,
                        title = title,
                        description = dialogBinding.descriptionInput.text?.toString().orEmpty(),
                        anchorDateTimeMillis = selectedDueDateTime?.toInstant()?.toEpochMilli(),
                        recurrence = selectedRecurrence,
                        weeklyDaysMask = weeklyMask,
                        isCompleted = reminder?.isCompleted ?: false,
                        endDateTimeMillis = endMillis
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

    private fun pickDateTime(initial: ZonedDateTime, onResult: (ZonedDateTime) -> Unit) {
        val base = initial.withSecond(0).withNano(0)
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val withDate = base.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
                pickTimeWithWheel(withDate, onResult)
            },
            base.year,
            base.monthValue - 1,
            base.dayOfMonth
        ).show()
    }

    private fun pickTimeWithWheel(initial: ZonedDateTime, onResult: (ZonedDateTime) -> Unit) {
        val binding = DialogTimePickerBinding.inflate(layoutInflater)
        binding.hourPicker.apply {
            minValue = 0
            maxValue = 23
            value = initial.hour
            setFormatter { value -> String.format("%02d", value) }
        }
        binding.minutePicker.apply {
            minValue = 0
            maxValue = 59
            value = initial.minute
            setFormatter { value -> String.format("%02d", value) }
        }
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.reminder_pick_time)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val result = initial.withHour(binding.hourPicker.value)
                    .withMinute(binding.minutePicker.value)
                    .withSecond(0)
                    .withNano(0)
                onResult(result)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun attachSwipeToCompletedRecycler(
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        adapter: CompletedEntryAdapter
    ) {
        val existing = recyclerView.getTag(R.id.tag_completed_swipe_helper) as? androidx.recyclerview.widget.ItemTouchHelper
        existing?.attachToRecyclerView(null)
        val callback = object : androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(0,
            androidx.recyclerview.widget.ItemTouchHelper.LEFT or androidx.recyclerview.widget.ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: androidx.recyclerview.widget.RecyclerView,
                viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
                target: androidx.recyclerview.widget.RecyclerView.ViewHolder
            ): Boolean = false

            override fun clearView(
                recyclerView: androidx.recyclerview.widget.RecyclerView,
                viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.translationX = 0f
                viewHolder.itemView.alpha = 1f
            }

            override fun onSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val reminder = adapter.reminderAt(position)
                if (reminder == null) {
                    adapter.notifyItemChanged(position)
                    return
                }
                when (direction) {
                    androidx.recyclerview.widget.ItemTouchHelper.LEFT ->
                        promptRestoreCompleted(reminder, adapter, position)
                    androidx.recyclerview.widget.ItemTouchHelper.RIGHT ->
                        promptDeleteCompleted(reminder, adapter, position)
                    else -> adapter.notifyItemChanged(position)
                }
            }
        }
        val helper = androidx.recyclerview.widget.ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerView)
        recyclerView.setTag(R.id.tag_completed_swipe_helper, helper)
    }

    private fun promptRestoreCompleted(reminder: Reminder, adapter: CompletedEntryAdapter, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.reminder_restore_completed_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                adapter.notifyItemChanged(position)
                restoreCompletedReminder(reminder)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                adapter.notifyItemChanged(position)
            }
            .setOnCancelListener { adapter.notifyItemChanged(position) }
            .show()
    }

    private fun promptDeleteCompleted(reminder: Reminder, adapter: CompletedEntryAdapter, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.reminder_delete_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                adapter.notifyItemChanged(position)
                viewLifecycleOwner.lifecycleScope.launch {
                    reminderRepository.deleteReminder(reminder)
                }
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                adapter.notifyItemChanged(position)
            }
            .setOnCancelListener { adapter.notifyItemChanged(position) }
            .show()
    }

    private fun restoreCompletedReminder(reminder: Reminder) {
        viewLifecycleOwner.lifecycleScope.launch {
            reminderRepository.updateReminder(reminder.copy(isCompleted = false))
        }
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
        private val DATE_TIME_SUMMARY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d HH:mm")
    }
}
