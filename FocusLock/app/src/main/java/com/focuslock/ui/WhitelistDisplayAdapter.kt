package com.focuslock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.focuslock.databinding.ItemWhitelistAppBinding
import com.focuslock.model.WhitelistedApp

class WhitelistDisplayAdapter(
    private val onRemove: (WhitelistedApp) -> Unit,
    private val iconProvider: (String) -> android.graphics.drawable.Drawable?
) : RecyclerView.Adapter<WhitelistDisplayAdapter.ViewHolder>() {

    private val items = mutableListOf<WhitelistedApp>()

    fun submitList(list: List<WhitelistedApp>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWhitelistAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemWhitelistAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(app: WhitelistedApp) {
            binding.appLabel.text = app.label
            val icon = iconProvider(app.packageName)
            binding.appIcon.setImageDrawable(
                icon ?: ContextCompat.getDrawable(
                    binding.root.context,
                    android.R.drawable.sym_def_app_icon
                )
            )
            binding.root.setOnLongClickListener {
                onRemove(app)
                true
            }
        }
    }
}
