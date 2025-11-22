package com.focuslock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemCompletedDayBinding
import com.focuslock.model.Reminder
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class CompletedDayGroup(
    val dateMillis: Long,
    val reminders: List<Reminder>
)

class CompletedDayAdapter(
    private val onReminderClick: (Reminder) -> Unit,
    private val swipeAttacher: (RecyclerView, CompletedEntryAdapter) -> Unit
) : ListAdapter<CompletedDayGroup, CompletedDayAdapter.ViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCompletedDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCompletedDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val entryAdapter = CompletedEntryAdapter(onReminderClick)

        init {
            binding.completedEntryRecycler.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(binding.root.context)
            binding.completedEntryRecycler.adapter = entryAdapter
            swipeAttacher(binding.completedEntryRecycler, entryAdapter)
        }

        fun bind(group: CompletedDayGroup) {
            val zone = ZoneId.systemDefault()
            val label = if (group.dateMillis == Long.MAX_VALUE) {
                binding.root.context.getString(R.string.reminder_completed_floating_group_title)
            } else {
                DATE_FORMATTER.format(
                    java.time.Instant.ofEpochMilli(group.dateMillis).atZone(zone)
                )
            }
            binding.completedDateLabel.text = label
            entryAdapter.submitList(group.reminders)
        }
    }

    private object Diff : DiffUtil.ItemCallback<CompletedDayGroup>() {
        override fun areItemsTheSame(oldItem: CompletedDayGroup, newItem: CompletedDayGroup): Boolean {
            return oldItem.dateMillis == newItem.dateMillis
        }

        override fun areContentsTheSame(oldItem: CompletedDayGroup, newItem: CompletedDayGroup): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, EEEE")
    }
}
