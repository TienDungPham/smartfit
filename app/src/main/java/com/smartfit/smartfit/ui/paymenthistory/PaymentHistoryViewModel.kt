package com.smartfit.smartfit.ui.paymenthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel

class PaymentHistoryViewModel(private val appRepository: AppRepository) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}