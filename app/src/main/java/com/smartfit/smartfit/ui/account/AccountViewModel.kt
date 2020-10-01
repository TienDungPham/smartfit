package com.smartfit.smartfit.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AccountViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userProfile = appRepository.findUserProfile().asLiveData()

    fun syncUserProfile() {
        viewModelScope.launch {
            appRepository.syncUserProfile()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}