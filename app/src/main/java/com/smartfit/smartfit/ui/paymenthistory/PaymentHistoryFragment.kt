package com.smartfit.smartfit.ui.paymenthistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentPaymentHistoryBinding

class PaymentHistoryFragment : Fragment() {
    private lateinit var binding: FragmentPaymentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}