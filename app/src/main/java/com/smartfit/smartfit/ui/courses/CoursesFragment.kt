package com.smartfit.smartfit.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCoursesBinding
import javax.inject.Inject

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val coursesViewModel by viewModels<CoursesViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCoursesFragment(this)

        val courseAdapter = CourseAdapter {
            val bundle = Bundle()
            bundle.putLong("courseId", it)
            findNavController().navigate(
                R.id.action_nav_courses_to_nav_course_preview,
                bundle
            )
        }
        binding.courseRv.layoutManager = LinearLayoutManager(requireContext())
        binding.courseRv.adapter = courseAdapter

        coursesViewModel.courses.observe(viewLifecycleOwner) {
            if (it != null) courseAdapter.submitList(it)
        }
        coursesViewModel.syncCourse()

        return binding.root
    }
}