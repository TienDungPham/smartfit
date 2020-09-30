package com.smartfit.smartfit.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.entity.Meal
import com.smartfit.smartfit.databinding.ItemMealBinding

class MealAdapter :
    ListAdapter<Meal, MealAdapter.ViewHolder>(MealDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Meal, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMealBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MealDiffUtil : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.name == newItem.name
    }
}