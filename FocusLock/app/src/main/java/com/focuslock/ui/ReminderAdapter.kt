package com.focuslock.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
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
            val descriptionVisible = reminder.description.isNotBlank()
            binding.reminderDescription.apply {
                text = reminder.description
                visibility = if (descriptionVisible) View.VISIBLE else View.GONE
            }
            binding.reminderTitle.text = reminder.title

            val dueMillis = reminder.nextOccurrenceMillis() ?: reminder.anchorDateTimeMillis
            val dueText = Instant.ofEpochMilli(dueMillis)
                .atZone(ZoneId.systemDefault())
                .format(DATE_TIME_FORMATTER)
            binding.reminderDue.text = context.getString(R.string.reminder_due_prefix, dueText)
            binding.reminderRecurrence.text = recurrenceLabel(context, reminder)

            val isActiveDisplay = reminder.isActive && !reminder.isArchived
            val titleColor = if (isActiveDisplay) {
                ContextCompat.getColor(context, R.color.white)
            } else {
                ContextCompat.getColor(context, R.color.reminder_text_inactive)
            }
            val secondaryActive = ContextCompat.getColor(context, R.color.reminder_text_secondary)
            val secondaryColor = if (isActiveDisplay) secondaryActive else {
                ContextCompat.getColor(context, R.color.reminder_text_inactive)
            }
            val dueColor = if (isActiveDisplay) {
                ContextCompat.getColor(context, R.color.teal_200)
            } else {
                ContextCompat.getColor(context, R.color.reminder_text_inactive)
            }
            binding.reminderTitle.setTextColor(titleColor)
            binding.reminderDescription.setTextColor(secondaryColor)
            binding.reminderRecurrence.setTextColor(secondaryColor)
            binding.reminderDue.setTextColor(dueColor)

            val statusInfo = when {
                reminder.isCompleted -> StatusInfo(
                    context.getString(R.string.reminder_status_completed),
                    ContextCompat.getColor(context, R.color.reminder_status_completed)
                )
                reminder.isArchived -> StatusInfo(
                    context.getString(R.string.reminder_status_archived),
                    ContextCompat.getColor(context, R.color.reminder_status_archived)
                )
                !reminder.isActive -> StatusInfo(
                    context.getString(R.string.reminder_status_inactive),
                    ContextCompat.getColor(context, R.color.reminder_status_inactive)
                )
                else -> null
            }
            if (statusInfo != null) {
                binding.reminderStatus.visibility = View.VISIBLE
                binding.reminderStatus.text = statusInfo.text
                ViewCompat.setBackgroundTintList(
                    binding.reminderStatus,
                    ColorStateList.valueOf(statusInfo.color)
                )
            } else {
                binding.reminderStatus.visibility = View.GONE
            }

            val activeColor = ContextCompat.getColor(context, R.color.plan_active_green)
            val inactiveColor = ContextCompat.getColor(context, R.color.plan_inactive_gray)
            binding.reminderToggle.text = if (reminder.isActive) {
                context.getString(R.string.status_on)
            } else {
                context.getString(R.string.status_off)
            }
            binding.reminderToggle.backgroundTintList = ColorStateList.valueOf(
                if (reminder.isActive) activeColor else inactiveColor
            )
            binding.reminderToggle.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            binding.reminderToggle.setOnClickListener {
                toggleListener(reminder)
            }

            binding.completeButton.isEnabled = !reminder.isRepeating && !reminder.isCompleted
            binding.completeButton.alpha = if (binding.completeButton.isEnabled) 1f else 0.4f
            binding.completeButton.setOnClickListener {
                completeListener(reminder)
            }

            val canArchive = reminder.isCompleted || !reminder.isActive
            binding.archiveButton.isEnabled = canArchive
            binding.archiveButton.alpha = if (canArchive) 1f else 0.4f
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

    private data class StatusInfo(val text: String, val color: Int)

    private object Diff : DiffUtil.ItemCallback<Reminder>() {
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm", Locale.getDefault())
    }
}
