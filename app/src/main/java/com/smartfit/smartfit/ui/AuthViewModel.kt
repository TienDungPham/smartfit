package com.smartfit.smartfit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.transfer.up.SignIn
import com.smartfit.smartfit.data.transfer.up.SignUp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AuthViewModel(private val appRepository: AppRepository) : ViewModel() {
    var phoneNumber: String? = null
    var phoneIdentity: String? = null
    var verificationId: String? = null

    val isVerifySuccess: LiveData<Boolean>
        get() {
            return _isVerifySuccess
        }
    private val _isVerifySuccess = MutableLiveData<Boolean>()

    val isSignInSuccess: LiveData<Boolean>
        get() {
            return _isSignInSuccess
        }
    private val _isSignInSuccess = MutableLiveData<Boolean>()

    val isSignUpSuccess: LiveData<Boolean>
        get() {
            return _isSignUpSuccess
        }
    private val _isSignUpSuccess = MutableLiveData<Boolean>()

    fun signIn() {
        viewModelScope.launch {
            val signIn = SignIn(phoneIdentity!!, phoneNumber!!)
            _isSignInSuccess.value = appRepository.signIn(signIn)
        }
    }

    fun signUp(name: String, weight: Int, height: Int, age: Int, gender: Boolean, goal: String) {
        viewModelScope.launch {
            val signUp =
                SignUp(name, weight, height, age, gender, goal, phoneIdentity!!, phoneNumber!!)
            _isSignUpSuccess.value = appRepository.signUp(signUp)
        }
    }

    fun verifySuccess() {
        _isVerifySuccess.value = true
    }

    fun verifyFail() {
        _isVerifySuccess.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}