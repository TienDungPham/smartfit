package com.smartfit.smartfit.ui.addmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentAddMealBinding
import javax.inject.Inject

class AddMealFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddMealBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val addMealViewModel by viewModels<AddMealViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMealBinding.inflate(inflater, container, false)
        appComponent(requireContext()).injectAddMealFragment(this)

        return binding.root
    }
}