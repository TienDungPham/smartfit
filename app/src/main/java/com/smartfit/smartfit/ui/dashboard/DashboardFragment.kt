package com.smartfit.smartfit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        userCourseAdapter = UserCourseAdapter()
        binding.includeUserCourses.recentCoursesRv.layoutManager =
            LinearLayoutManager(requireContext())
        binding.includeUserCourses.recentCoursesRv.adapter = userCourseAdapter

        dashboardViewModel.userProgress.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.includeUserProgress.apply {
                workoutsValue.text = it.workouts.toString()
                minutesValue.text = it.minutes
                caloriesValue.text = "${it.calories.toString()} Cal"
                caloriesLeft.text = (it.goal - it.calories).toString()
                if (it.goal != 0) {
                    val percent = it.calories.toFloat() / it.goal.toFloat() * 100F
                    progressBar.progress = percent.roundToInt()
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