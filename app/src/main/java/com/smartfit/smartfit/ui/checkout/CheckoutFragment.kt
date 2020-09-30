package com.smartfit.smartfit.ui.checkout

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
import com.smartfit.smartfit.databinding.FragmentCheckoutBinding
import javax.inject.Inject

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val checkoutViewModel by viewModels<CheckoutViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCheckoutFragment(this)

        return binding.root
    }
}