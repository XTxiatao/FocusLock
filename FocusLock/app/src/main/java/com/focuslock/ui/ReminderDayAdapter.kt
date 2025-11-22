package com.focuslock.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemReminderDayBinding
import com.focuslock.model.Reminder
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ReminderDayGroup(
    val dateMillis: Long,
    val reminders: List<Reminder>
)

class ReminderDayAdapter(
    private val onReminderClick: (Reminder) -> Unit,
    private val onCompleteClick: (Reminder) -> Unit
) : ListAdapter<ReminderDayGroup, ReminderDayAdapter.DayViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemReminderDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DayViewHolder(
        private val binding: ItemReminderDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val entryAdapter = ReminderEntryAdapter(onReminderClick, onCompleteClick)

        init {
            binding.reminderEntryRecycler.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(binding.root.context)
            binding.reminderEntryRecycler.adapter = entryAdapter
        }

        fun bind(group: ReminderDayGroup) {
            val zone = ZoneId.systemDefault()
            if (group.dateMillis == Long.MAX_VALUE) {
                binding.dateLabel.visibility = View.GONE
            } else {
                binding.dateLabel.visibility = View.VISIBLE
                binding.dateLabel.text = DATE_FORMATTER.format(
                    java.time.Instant.ofEpochMilli(group.dateMillis).atZone(zone)
                )
            }
            entryAdapter.submitList(group.reminders)
        }
    }

    private object Diff : DiffUtil.ItemCallback<ReminderDayGroup>() {
        override fun areItemsTheSame(oldItem: ReminderDayGroup, newItem: ReminderDayGroup): Boolean {
            return oldItem.dateMillis == newItem.dateMillis
        }

        override fun areContentsTheSame(oldItem: ReminderDayGroup, newItem: ReminderDayGroup): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, EEEE")
    }
}
