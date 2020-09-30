package com.smartfit.smartfit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentDashboardBinding
import com.smartfit.smartfit.ui.MainActivity
import javax.inject.Inject

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val dashboardViewModel by viewModels<DashboardViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).showBottomAppBar()
        appComponent(requireContext()).injectDashboardFragment(this)

        return binding.root
    }
}