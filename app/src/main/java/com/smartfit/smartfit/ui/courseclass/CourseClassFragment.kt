package com.smartfit.smartfit.ui.courseclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentCourseClassBinding

class CourseClassFragment : Fragment() {
    private lateinit var binding: FragmentCourseClassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseClassBinding.inflate(inflater, container, false)
        return binding.root
    }
}