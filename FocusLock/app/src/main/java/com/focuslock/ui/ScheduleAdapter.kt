package com.focuslock.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.R
import com.focuslock.databinding.ItemLockScheduleBinding
import com.focuslock.model.LockSchedule

class ScheduleAdapter(
    private val toggleListener: (LockSchedule) -> Unit,
    private val longPressListener: (LockSchedule) -> Unit,
    private val editListener: (LockSchedule) -> Unit
) : ListAdapter<LockSchedule, ScheduleAdapter.ScheduleViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemLockScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ScheduleViewHolder(
        private val binding: ItemLockScheduleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: LockSchedule) {
            val context = binding.root.context
            val activeColor = ContextCompat.getColor(context, R.color.plan_active_green)
            val inactiveColor = ContextCompat.getColor(context, R.color.plan_inactive_gray)

            binding.timeRangeText.text = schedule.rangeLabel()
            binding.daysText.text = schedule.dayLabels()
            val statusText = if (schedule.isEnabled) {
                context.getString(R.string.status_on)
            } else {
                context.getString(R.string.status_off)
            }
            val tintColor = if (schedule.isEnabled) activeColor else inactiveColor
            binding.timeRangeText.setTextColor(tintColor)
            binding.daysText.setTextColor(tintColor)
            binding.toggleButton.text = statusText
            binding.toggleButton.backgroundTintList = ColorStateList.valueOf(tintColor)
            binding.toggleButton.setTextColor(ContextCompat.getColor(context, android.R.color.white))

            binding.toggleButton.setOnClickListener {
                toggleListener(schedule)
            }

            binding.root.setOnClickListener {
                editListener(schedule)
            }

            binding.root.setOnLongClickListener {
                longPressListener(schedule)
                true
            }
        }
    }

    private object Diff : DiffUtil.ItemCallback<LockSchedule>() {
        override fun areItemsTheSame(oldItem: LockSchedule, newItem: LockSchedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LockSchedule, newItem: LockSchedule): Boolean {
            return oldItem == newItem
        }
    }
}
