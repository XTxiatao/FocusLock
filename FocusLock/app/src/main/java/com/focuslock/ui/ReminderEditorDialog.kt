package com.focuslock.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.focuslock.R
import com.focuslock.data.ReminderRepository
import com.focuslock.databinding.DialogReminderEditorBinding
import com.focuslock.databinding.DialogTimePickerBinding
import com.focuslock.model.Reminder
import com.focuslock.model.ReminderRecurrence
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun showReminderEditorDialog(
    context: Context,
    reminder: Reminder?,
    repository: ReminderRepository,
    scope: CoroutineScope,
    overlayWindow: Boolean = false,
    onDismiss: (() -> Unit)? = null
): AlertDialog {
    val inflater = LayoutInflater.from(context)
    val dialogBinding = DialogReminderEditorBinding.inflate(inflater)
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
            ContextCompat.getColor(context, R.color.plan_active_green)
        } else {
            ContextCompat.getColor(context, R.color.plan_inactive_gray)
        }
        button.backgroundTintList = ColorStateList.valueOf(color)
        button.setTextColor(ContextCompat.getColor(context, android.R.color.white))
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
        } ?: context.getString(R.string.reminder_no_due_time)
    }

    fun updateEndUi() {
        val active = dueEnabled && endEnabled
        dialogBinding.endToggleButton.isEnabled = dueEnabled
        updateToggleButton(dialogBinding.endToggleButton, active)
        dialogBinding.endDateTimeButton.isEnabled = active
        dialogBinding.endDateTimeButton.text = selectedEndDateTime?.let {
            DATE_TIME_SUMMARY_FORMATTER.format(it)
        } ?: context.getString(R.string.reminder_no_end_time)
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
        pickDateTime(context, ensureDueDateTime(), overlayWindow) { picked ->
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
        pickDateTime(context, ensureEndDateTime(), overlayWindow) { picked ->
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
        override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
            selectedRecurrence = recurrenceOptions[position]
            if (selectedRecurrence != ReminderRecurrence.NONE) {
                lastRecurrenceSelection = selectedRecurrence
            }
            updateRecurrenceUi()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }

    val dialogTitle = if (reminder == null) {
        context.getString(R.string.reminder_add_button)
    } else {
        context.getString(R.string.reminder_edit_title)
    }

    val dialog = AlertDialog.Builder(context)
        .setTitle(dialogTitle)
        .setView(dialogBinding.root)
        .setPositiveButton(R.string.reminder_save_button, null)
        .setNegativeButton(R.string.reminder_cancel_button, null)
        .create()

    if (overlayWindow) {
        val type = overlayWindowType()
        dialog.window?.setType(type)
    }

    fun showMessage(@StringRes messageId: Int) {
        if (overlayWindow) {
            AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(android.R.string.ok, null)
                .create().also {
                    it.window?.setType(overlayWindowType())
                }
                .show()
        } else {
            Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
        }
    }

    dialog.setOnShowListener {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val title = dialogBinding.titleInput.text?.toString()?.trim().orEmpty()
            if (title.isBlank()) {
                showMessage(R.string.reminder_title_required)
                return@setOnClickListener
            }
            val weeklyMask = if (selectedRecurrence == ReminderRecurrence.WEEKLY) {
                if (weeklySelection.isEmpty()) {
                    showMessage(R.string.reminder_weekly_required)
                    return@setOnClickListener
                }
                encodeWeekDays(weeklySelection)
            } else {
                0
            }
            val endMillis = selectedEndDateTime?.toInstant()?.toEpochMilli()
            val saveScope = CoroutineScope(scope.coroutineContext + Dispatchers.Main.immediate)
            saveScope.launch {
                val unique = repository.ensureUniqueTitle(title, reminder?.id)
                if (!unique) {
                    showMessage(R.string.reminder_duplicate_title)
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
                    repository.addReminder(updatedReminder)
                } else {
                    repository.updateReminder(updatedReminder)
                }
                dialog.dismiss()
            }
        }
    }

    dialog.setOnDismissListener {
        onDismiss?.invoke()
    }

    dialog.show()
    return dialog
}

private fun encodeWeekDays(days: Set<DayOfWeek>): Int {
    var mask = 0
    days.forEach { day ->
        mask = mask or (1 shl day.ordinal)
    }
    return mask
}

private fun pickDateTime(
    context: Context,
    initial: ZonedDateTime,
    overlayWindow: Boolean,
    onResult: (ZonedDateTime) -> Unit
) {
    val base = initial.withSecond(0).withNano(0)
    val dateDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val withDate = base.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
            pickTimeWithWheel(context, withDate, overlayWindow, onResult)
        },
        base.year,
        base.monthValue - 1,
        base.dayOfMonth
    )
    if (overlayWindow) {
        dateDialog.window?.setType(overlayWindowType())
    }
    dateDialog.show()
}

private fun pickTimeWithWheel(
    context: Context,
    initial: ZonedDateTime,
    overlayWindow: Boolean,
    onResult: (ZonedDateTime) -> Unit
) {
    val binding = DialogTimePickerBinding.inflate(LayoutInflater.from(context))
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
    val dialog = AlertDialog.Builder(context)
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
        .create()
    if (overlayWindow) {
        dialog.window?.setType(overlayWindowType())
    }
    dialog.show()
}

private fun overlayWindowType(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
    } else {
        WindowManager.LayoutParams.TYPE_PHONE
    }
}

private val DATE_TIME_SUMMARY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d HH:mm")
