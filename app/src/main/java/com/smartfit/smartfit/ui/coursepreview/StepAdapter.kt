package com.smartfit.smartfit.ui.coursepreview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.databinding.ItemCourseStepBinding

class StepAdapter :
    ListAdapter<CourseStep, StepAdapter.ViewHolder>(StepDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemCourseStepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CourseStep, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCourseStepBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class StepDiffUtil : DiffUtil.ItemCallback<CourseStep>() {
    override fun areItemsTheSame(oldItem: CourseStep, newItem: CourseStep): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CourseStep, newItem: CourseStep): Boolean {
        return oldItem.name == newItem.name
    }
}