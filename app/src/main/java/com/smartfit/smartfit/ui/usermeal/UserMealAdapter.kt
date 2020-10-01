package com.smartfit.smartfit.ui.usermeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartfit.smartfit.R
import com.smartfit.smartfit.data.entity.UserMeal
import com.smartfit.smartfit.databinding.ItemUserMealBinding

class UserMealAdapter :
    ListAdapter<UserMeal, UserMealAdapter.ViewHolder>(UserMealDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemUserMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserMeal, position: Int) {
            binding.mealName.text = item.mealName
            binding.mealSize.text = "${item.totalCalories} Cal"
            when (item.mealType) {
                "Carbs" -> {
                    Glide.with(binding.mealImage)
                        .load("")
                        .error(R.drawable.meal_blue)
                        .into(binding.mealImage)
                }
                "Protein" -> {
                    Glide.with(binding.mealImage)
                        .load("")
                        .error(R.drawable.meal_green)
                        .into(binding.mealImage)
                }
                else -> {
                    Glide.with(binding.mealImage)
                        .load("")
                        .error(R.drawable.meal_orange)
                        .into(binding.mealImage)
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserMealBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UserMealDiffUtil : DiffUtil.ItemCallback<UserMeal>() {
    override fun areItemsTheSame(oldItem: UserMeal, newItem: UserMeal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserMeal, newItem: UserMeal): Boolean {
        return oldItem.mealName == newItem.mealName
    }
}