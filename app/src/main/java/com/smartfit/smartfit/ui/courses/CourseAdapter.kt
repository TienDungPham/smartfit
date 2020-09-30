package com.smartfit.smartfit.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.databinding.ItemCourseBinding

class CourseAdapter :
    ListAdapter<Course, CourseAdapter.ViewHolder>(CourseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Course, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCourseBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CourseDiffUtil : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.name == newItem.name
    }
}