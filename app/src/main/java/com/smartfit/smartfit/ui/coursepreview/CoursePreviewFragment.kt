package com.smartfit.smartfit.ui.coursepreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentCoursePreviewBinding

class CoursePreviewFragment : Fragment() {
    private lateinit var binding: FragmentCoursePreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }
}