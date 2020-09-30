package com.smartfit.smartfit.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.data.entity.UserCourse
import com.smartfit.smartfit.databinding.ItemCourseStepBinding
import com.smartfit.smartfit.databinding.ItemUserCourseBinding
import com.smartfit.smartfit.ui.coursepreview.StepAdapter

class UserCourseAdapter :
    ListAdapter<UserCourse, UserCourseAdapter.ViewHolder>(UserCourseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class ViewHolder(private val binding: ItemUserCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserCourse, position: Int) {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserCourseBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UserCourseDiffUtil : DiffUtil.ItemCallback<UserCourse>() {
    override fun areItemsTheSame(oldItem: UserCourse, newItem: UserCourse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserCourse, newItem: UserCourse): Boolean {
        return oldItem.courseName == newItem.courseName
    }
}