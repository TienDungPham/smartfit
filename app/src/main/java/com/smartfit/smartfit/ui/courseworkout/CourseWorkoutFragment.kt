package com.smartfit.smartfit.ui.courseworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentCourseWorkoutBinding

class CourseWorkoutFragment : Fragment() {
    private lateinit var binding: FragmentCourseWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}