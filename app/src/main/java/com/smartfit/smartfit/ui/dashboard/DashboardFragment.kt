package com.smartfit.smartfit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentDashboardBinding
import com.smartfit.smartfit.ui.MainActivity
import javax.inject.Inject
import kotlin.math.roundToInt

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val dashboardViewModel by viewModels<DashboardViewModel> {
        viewModelFactory
    }

    private lateinit var userCourseAdapter: UserCourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).showBottomAppBar()
        appComponent(requireContext()).injectDashboardFragment(this)

        userCourseAdapter = UserCourseAdapter {
            (requireActivity() as MainActivity).hideBottomAppBar()
            val bundle = Bundle()
            bundle.putLong("courseId", it)
            findNavController().navigate(
                R.id.action_nav_dashboard_to_nav_course_preview,
                bundle
            )
        }
        binding.includeUserCourses.recentCoursesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userCourseAdapter
        }

        dashboardViewModel.userProgress.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.includeUserProgress.apply {
                workoutsValue.text = it.workouts.toString()
                minutesValue.text = it.minutes
                caloriesValue.text = "${it.calories} Cal"
                caloriesLeft.text = "${it.goal - it.calories} Cal"

                if (it.goal != 0) {
                    val percent = it.calories.toFloat() / it.goal.toFloat() * 100F
                    progressBar.progress = percent.roundToInt()
                    val goalPercent = String.format("%.2f", percent)
                    goalMessage.text = "You have walked ${goalPercent}% of your goal"
                }
            }
        }
        dashboardViewModel.userCourses.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            userCourseAdapter.submitList(it)
        }
        dashboardViewModel.syncData()

        return binding.root
    }
}