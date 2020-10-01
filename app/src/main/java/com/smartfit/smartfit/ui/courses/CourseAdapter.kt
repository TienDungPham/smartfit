package com.smartfit.smartfit.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.databinding.ItemCourseBinding

class CourseAdapter(private val callback: (Long) -> Unit) :
    ListAdapter<Course, CourseAdapter.ViewHolder>(CourseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, callback)
    }

    class ViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Course, position: Int, callback: (Long) -> Unit) {
            binding.courseName.text = item.name
            binding.courseVariant.text =
                "${item.courseType} - ${item.level} - ${item.estimatedTime}H"
            binding.startButton.setOnClickListener {
                callback(item.id)
            }

            Glide.with(binding.courseImage)
                .load(item.imageUrl)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.courseImage)
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