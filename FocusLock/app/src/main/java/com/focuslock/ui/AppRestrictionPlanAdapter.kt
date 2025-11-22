package com.focuslock.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.focuslock.R
import com.focuslock.databinding.ItemAppRestrictionPlanBinding
import com.focuslock.databinding.ItemWhitelistAppBinding
import com.focuslock.model.AppRestrictionPlan

class AppRestrictionPlanAdapter(
    private val onToggle: (AppRestrictionPlan) -> Unit,
    private val onLongPress: (AppRestrictionPlan) -> Unit,
    private val onEdit: (AppRestrictionPlan) -> Unit,
    private val iconProvider: (String) -> android.graphics.drawable.Drawable?
) : RecyclerView.Adapter<AppRestrictionPlanAdapter.ViewHolder>() {

    private val items = mutableListOf<AppRestrictionPlan>()

    fun submitList(list: List<AppRestrictionPlan>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAppRestrictionPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemAppRestrictionPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: AppRestrictionPlan) {
            val context = binding.root.context
            binding.planRange.text = plan.rangeLabel()
            binding.planDays.text = plan.dayLabels()
            val tintColor = ContextCompat.getColor(
                context,
                if (plan.isEnabled) R.color.plan_active_green else R.color.plan_inactive_gray
            )
            val statusText = context.getString(if (plan.isEnabled) R.string.status_on else R.string.status_off)
            binding.planRange.setTextColor(tintColor)
            binding.planDays.setTextColor(tintColor)
            binding.toggleButton.text = statusText
            binding.toggleButton.backgroundTintList = ColorStateList.valueOf(tintColor)
            binding.toggleButton.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            binding.toggleButton.setOnClickListener { onToggle(plan) }
            binding.root.setOnClickListener { onEdit(plan) }
            binding.root.setOnLongClickListener {
                onLongPress(plan)
                true
            }

            binding.appListContainer.removeAllViews()
            if (plan.apps.isEmpty()) {
                binding.appListContainer.isVisible = false
            } else {
                binding.appListContainer.isVisible = true
                val inflater = LayoutInflater.from(context)
                plan.apps.forEach { app ->
                    val appBinding =
                        ItemWhitelistAppBinding.inflate(inflater, binding.appListContainer, false)
                    appBinding.appLabel.text = app.label
                    val icon = iconProvider(app.packageName)
                    appBinding.appIcon.setImageDrawable(
                        icon ?: ContextCompat.getDrawable(context, android.R.drawable.sym_def_app_icon)
                    )
                    val params = FlexboxLayout.LayoutParams(
                        context.resources.getDimensionPixelSize(R.dimen.app_icon_item_width),
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(8, 8, 8, 8)
                    }
                    binding.appListContainer.addView(appBinding.root, params)
                }
            }
        }
    }
}
