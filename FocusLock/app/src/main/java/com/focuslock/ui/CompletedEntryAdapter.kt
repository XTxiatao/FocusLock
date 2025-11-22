package com.focuslock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemCompletedEntryBinding
import com.focuslock.model.Reminder
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CompletedEntryAdapter(
    private val onReminderClick: (Reminder) -> Unit
) : ListAdapter<Reminder, CompletedEntryAdapter.EntryViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding = ItemCompletedEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EntryViewHolder(
        private val binding: ItemCompletedEntryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reminder: Reminder) {
            val zone = ZoneId.systemDefault()
            val timeText = reminder.anchorDateTimeMillis?.let {
                TIME_FORMATTER.format(
                    java.time.Instant.ofEpochMilli(it).atZone(zone)
                )
            } ?: binding.root.context.getString(R.string.reminder_no_due_time)
            binding.completedEntryTitle.text = reminder.title
            binding.completedEntryTime.text = timeText
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

    fun reminderAt(position: Int): Reminder? = currentList.getOrNull(position)
}
