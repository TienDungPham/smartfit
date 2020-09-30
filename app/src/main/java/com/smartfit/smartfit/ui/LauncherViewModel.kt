package com.smartfit.smartfit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LauncherViewModel(private val appRepository: AppRepository) : ViewModel() {
    val isUserSignIn: LiveData<Boolean>
        get() {
            return _isUserSignIn
        }
    private val _isUserSignIn = MutableLiveData<Boolean>()

    fun checkUserSession() {
        viewModelScope.launch {
            _isUserSignIn.value = appRepository.isUserSignIn()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}