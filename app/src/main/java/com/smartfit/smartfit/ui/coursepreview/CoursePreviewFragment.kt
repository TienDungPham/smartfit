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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCoursePreviewBinding
import com.smartfit.smartfit.ui.MainActivity
import javax.inject.Inject

class CoursePreviewFragment : Fragment() {
    private lateinit var binding: FragmentCoursePreviewBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val coursePreviewViewModel by viewModels<CoursePreviewViewModel> {
        viewModelFactory
    }

    private var navigateCommand: String? = null
    private var stepId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursePreviewBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCoursePreviewFragment(this)

        binding.startButton.setOnClickListener {
            navigateCommand = "TO_WORKOUT"
            arguments?.getLong("courseId")?.let {
                coursePreviewViewModel.checkUserAccess(it)
                (requireActivity() as MainActivity).showLoadingDialog("Please wait.....")
            }
        }

        val courseStepAdapter = StepAdapter {
            navigateCommand = "TO_CLASS"
            stepId = it
            arguments?.getLong("courseId")?.let {
                coursePreviewViewModel.checkUserAccess(it)
                (requireActivity() as MainActivity).showLoadingDialog("Please wait.....")
            }
        }
        binding.courseStepRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = courseStepAdapter
        }

        coursePreviewViewModel.courseDetail.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            (requireActivity() as MainActivity).closeLoadingDialog()
            binding.apply {
                courseName.text = it.course.name
                courseVariant.text =
                    "${it.course.courseType} - ${it.course.level} - ${it.course.estimatedTime}H"
                courseDescription.text = it.course.description
                courseStepAdapter.submitList(it.steps.filter { s -> s.type == "Practice" })

                Glide.with(binding.courseImage)
                    .load(it.course.imageUrl)
                    .into(binding.courseImage)
            }
        }

        coursePreviewViewModel.isUserAllow.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).closeLoadingDialog()
            if (it == null) return@observe
            if (it) {
                coursePreviewViewModel.resetUserAccess()
                arguments?.getLong("courseId")?.let { cid ->
                    val bundle = Bundle()

                    if (navigateCommand == "TO_WORKOUT") {
                        bundle.putLong("courseId", cid)
                        findNavController().navigate(
                            R.id.action_nav_course_preview_to_nav_course_workout,
                            bundle
                        )
                    } else {
                        bundle.putLong("courseId", cid)
                        bundle.putLong("stepId", stepId!!)
                        findNavController().navigate(
                            R.id.action_nav_course_preview_to_nav_course_class,
                            bundle
                        )
                    }
                }
            } else {
                coursePreviewViewModel.resetUserAccess()
                findNavController().navigate(R.id.action_nav_course_preview_to_nav_checkout)
            }
        }

        arguments?.getLong("courseId")?.let {
            (requireActivity() as MainActivity).showLoadingDialog("Please wait.....")
            coursePreviewViewModel.findCourseDetail(it)
        }

        return binding.root
    }
}