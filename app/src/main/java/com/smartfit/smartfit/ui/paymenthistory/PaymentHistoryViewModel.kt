package com.smartfit.smartfit.ui.paymenthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PaymentHistoryViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userOrderWithPayments = appRepository.findUserOrderWithPayment().asLiveData()

    fun syncUserOrderWithPayments() {
        viewModelScope.launch {
            appRepository.syncUserOrderWithPayment()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}