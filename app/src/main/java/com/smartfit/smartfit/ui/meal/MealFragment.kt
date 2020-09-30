package com.smartfit.smartfit.ui.meal

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
import com.smartfit.smartfit.databinding.FragmentMealBinding
import javax.inject.Inject

class MealFragment : Fragment() {
    private lateinit var binding: FragmentMealBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mealViewModel by viewModels<MealViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectMealFragment(this)

        return binding.root
    }
}