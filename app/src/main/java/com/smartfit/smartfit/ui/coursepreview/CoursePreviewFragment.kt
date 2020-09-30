package com.smartfit.smartfit.ui.coursepreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCoursePreviewBinding
import javax.inject.Inject

class CoursePreviewFragment : Fragment() {
    private lateinit var binding: FragmentCoursePreviewBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val coursePreviewViewModel by viewModels<CoursePreviewViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursePreviewBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCoursePreviewFragment(this)

        return binding.root
    }
}