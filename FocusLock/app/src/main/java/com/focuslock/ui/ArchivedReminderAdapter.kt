package com.focuslock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemArchivedReminderBinding
import com.focuslock.model.Reminder
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class ArchivedReminderAdapter(
    private val restoreListener: (Reminder) -> Unit,
    private val deleteListener: (Reminder) -> Unit,
    private val editListener: (Reminder) -> Unit
) : ListAdapter<Reminder, ArchivedReminderAdapter.ArchivedReminderViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivedReminderViewHolder {
        val binding = ItemArchivedReminderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchivedReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchivedReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArchivedReminderViewHolder(
        private val binding: ItemArchivedReminderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reminder: Reminder) {
            val context = binding.root.context
            binding.archivedItemTitle.text = reminder.title
            val dueMillis = reminder.nextOccurrenceMillis() ?: reminder.anchorDateTimeMillis
            val dueText = Instant.ofEpochMilli(dueMillis)
                .atZone(ZoneId.systemDefault())
                .format(DATE_TIME_FORMATTER)
            binding.archivedItemDue.text = context.getString(R.string.reminder_due_prefix, dueText)
            binding.archivedItemStatus.text = when {
                reminder.isCompleted -> context.getString(R.string.reminder_status_completed)
                else -> context.getString(R.string.reminder_status_inactive)
            }

            binding.root.setOnClickListener {
                editListener(reminder)
            }
            binding.restoreButton.setOnClickListener { restoreListener(reminder) }
            binding.deleteButton.setOnClickListener { deleteListener(reminder) }
        }
    }

    private object Diff : DiffUtil.ItemCallback<Reminder>() {
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean = oldItem == newItem
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy • HH:mm", Locale.getDefault())
    }
}
