package com.smartfit.smartfit.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.ActionItem
import com.smartfit.smartfit.databinding.ItemSettingBinding

class SettingsAdapter :
    ListAdapter<ActionItem, SettingsAdapter.ViewHolder>(SettingsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemSettingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ActionItem, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSettingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SettingsDiffUtil : DiffUtil.ItemCallback<ActionItem>() {
    override fun areItemsTheSame(oldItem: ActionItem, newItem: ActionItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ActionItem, newItem: ActionItem): Boolean {
        return oldItem.title == newItem.title
    }
}