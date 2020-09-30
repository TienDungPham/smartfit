package com.smartfit.smartfit.ui.courseclass

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
import com.smartfit.smartfit.databinding.FragmentCourseClassBinding
import javax.inject.Inject

class CourseClassFragment : Fragment() {
    private lateinit var binding: FragmentCourseClassBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val courseClassViewModel by viewModels<CourseClassViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseClassBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCourseClassFragment(this)

        return binding.root
    }
}