package com.smartfit.smartfit.ui.paymenthistory

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
import com.smartfit.smartfit.databinding.FragmentPaymentHistoryBinding
import javax.inject.Inject

class PaymentHistoryFragment : Fragment() {
    private lateinit var binding: FragmentPaymentHistoryBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val paymentHistoryViewModel by viewModels<PaymentHistoryViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectPaymentHistoryFragment(this)

        return binding.root
    }
}