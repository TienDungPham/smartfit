package com.smartfit.smartfit.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel

class DashboardViewModel(private val appRepository: AppRepository) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}