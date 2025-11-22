package com.focuslock.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemReminderBinding
import com.focuslock.model.Reminder
import com.focuslock.model.ReminderRecurrence
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class ReminderAdapter(
    private val toggleListener: (Reminder) -> Unit,
    private val completeListener: (Reminder) -> Unit,
    private val archiveListener: (Reminder) -> Unit,
    private val editListener: (Reminder) -> Unit
) : ListAdapter<Reminder, ReminderAdapter.ReminderViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val binding = ItemReminderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReminderViewHolder(
        private val binding: ItemReminderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reminder: Reminder) {
            val context = binding.root.context
            binding.reminderTitle.text = reminder.title
            val descriptionVisible = reminder.description.isNotBlank()
            binding.reminderDescription.apply {
                text = reminder.description
                visibility = if (descriptionVisible) View.VISIBLE else View.GONE
            }

            val dueMillis = reminder.nextOccurrenceMillis() ?: reminder.anchorDateTimeMillis
            val dueText = Instant.ofEpochMilli(dueMillis)
                .atZone(ZoneId.systemDefault())
                .format(DATE_TIME_FORMATTER)
            binding.reminderDue.text = context.getString(R.string.reminder_due_prefix, dueText)

            binding.reminderRecurrence.text = recurrenceLabel(context, reminder)

            val statusText = when {
                reminder.isCompleted -> context.getString(R.string.reminder_status_completed)
                !reminder.isActive -> context.getString(R.string.reminder_status_inactive)
                reminder.isArchived -> context.getString(R.string.reminder_status_archived)
                else -> ""
            }
            binding.reminderStatus.apply {
                text = statusText
                visibility = if (statusText.isNotEmpty()) View.VISIBLE else View.GONE
            }

            binding.reminderToggle.setOnCheckedChangeListener(null)
            binding.reminderToggle.isChecked = reminder.isActive
            binding.reminderToggle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != reminder.isActive) {
                    toggleListener(reminder)
                }
            }

            binding.completeButton.isEnabled = !reminder.isRepeating && !reminder.isCompleted
            binding.completeButton.setOnClickListener {
                completeListener(reminder)
            }

            val canArchive = reminder.isCompleted || !reminder.isActive
            binding.archiveButton.isEnabled = canArchive
            binding.archiveButton.setOnClickListener {
                archiveListener(reminder)
            }

            binding.root.setOnClickListener {
                editListener(reminder)
            }
        }
    }

    private fun recurrenceLabel(context: android.content.Context, reminder: Reminder): String {
        return when (reminder.recurrence) {
            ReminderRecurrence.NONE -> context.getString(R.string.reminder_repeat_once)
            ReminderRecurrence.DAILY -> context.getString(R.string.reminder_repeat_daily)
            ReminderRecurrence.WEEKLY -> {
                val days = reminder.selectedDays().ifEmpty {
                    listOf(reminder.anchorDateTime().dayOfWeek)
                }
                val label = days.joinToString(", ") {
                    it.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                }
                context.getString(R.string.reminder_repeat_weekly, label)
            }
            ReminderRecurrence.MONTHLY -> context.getString(R.string.reminder_repeat_monthly)
            ReminderRecurrence.YEARLY -> context.getString(R.string.reminder_repeat_yearly)
        }
    }

    private object Diff : DiffUtil.ItemCallback<Reminder>() {
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy • HH:mm", Locale.getDefault())
    }
}
