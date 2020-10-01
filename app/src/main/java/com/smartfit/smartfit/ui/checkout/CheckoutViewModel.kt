package com.smartfit.smartfit.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.entity.UserAccess
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CheckoutViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userAccess: LiveData<UserAccess>
        get() {
            return _userAccess
        }
    private val _userAccess = MutableLiveData<UserAccess>()

    fun findUserAccess() {
        viewModelScope.launch {
            _userAccess.value = appRepository.findAccessToken()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}