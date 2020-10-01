package com.smartfit.smartfit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val appRepository: AppRepository) : ViewModel() {
    val isSignOutSuccess: LiveData<Boolean>
        get() {
            return _isSignOutSuccess
        }
    private val _isSignOutSuccess = MutableLiveData<Boolean>()

    fun signOut() {
        viewModelScope.launch {
            appRepository.signOut()
            _isSignOutSuccess.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}