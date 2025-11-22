package com.focuslock.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemReminderEntryBinding
import com.focuslock.model.Reminder
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ReminderEntryAdapter(
    private val onReminderClick: (Reminder) -> Unit,
    private val onCompleteClick: (Reminder) -> Unit
) : ListAdapter<Reminder, ReminderEntryAdapter.EntryViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding = ItemReminderEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EntryViewHolder(
        private val binding: ItemReminderEntryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reminder: Reminder) {
            val context = binding.root.context
            val zone = ZoneId.systemDefault()
            val dueText = TIME_FORMATTER.format(
                java.time.Instant.ofEpochMilli(reminder.anchorDateTimeMillis).atZone(zone)
            )
            val overdue = reminder.anchorDateTimeMillis < System.currentTimeMillis()
            binding.reminderTime.text = dueText
            val timeColor = if (overdue) {
                ContextCompat.getColor(context, R.color.reminder_due_overdue)
            } else {
                ContextCompat.getColor(context, R.color.white)
            }
            binding.reminderTime.setTextColor(timeColor)
            binding.reminderTitle.text = reminder.title
            binding.pillButton.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.reminder_text_inactive))
            binding.pillButton.setOnClickListener {
                onCompleteClick(reminder)
            }
            binding.root.setOnClickListener {
                onReminderClick(reminder)
            }
        }
    }

    private object Diff : DiffUtil.ItemCallback<Reminder>() {
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean = oldItem == newItem
    }

    companion object {
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
    }
}
