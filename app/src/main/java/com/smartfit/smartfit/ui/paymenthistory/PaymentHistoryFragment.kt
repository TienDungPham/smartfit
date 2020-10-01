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
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentPaymentHistoryBinding
import java.text.SimpleDateFormat
import java.util.*
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

        val paymentAdapter = PaymentAdapter()
        binding.paymentRv.layoutManager = LinearLayoutManager(requireContext())
        binding.paymentRv.adapter = paymentAdapter

        paymentHistoryViewModel.userOrderWithPayments.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.includeUserOrder.apply {
                statusValue.text = it.order.status
                typeValue.text = it.order.type
                var format = SimpleDateFormat("yyyy-MM-dd")
                val newDate: Date = format.parse(it.order.expiryTime)

                format = SimpleDateFormat("yyyy-MM-dd")
                val date: String = format.format(newDate)
                expiryTimeValue.text = date
            }
            paymentAdapter.submitList(it.payments)
        }

        paymentHistoryViewModel.syncUserOrderWithPayments()

        return binding.root
    }
}