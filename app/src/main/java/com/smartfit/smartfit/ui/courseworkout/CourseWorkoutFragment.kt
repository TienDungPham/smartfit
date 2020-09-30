package com.smartfit.smartfit.ui.courseworkout

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
import com.smartfit.smartfit.databinding.FragmentCourseWorkoutBinding
import javax.inject.Inject

class CourseWorkoutFragment : Fragment() {
    private lateinit var binding: FragmentCourseWorkoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val courseWorkoutViewModel by viewModels<CourseWorkoutViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseWorkoutBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCourseWorkoutFragment(this)

        return binding.root
    }
}