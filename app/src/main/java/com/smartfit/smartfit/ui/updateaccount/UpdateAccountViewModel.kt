package com.smartfit.smartfit.ui.updateaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.transfer.up.UpdateUserProfile
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UpdateAccountViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userProfile = appRepository.findUserProfile().asLiveData()

    fun syncUserProfile() {
        viewModelScope.launch {
            appRepository.syncUserProfile()
        }
    }

    fun updateUserProfile(
        name: String,
        imageUrl: String,
        weight: Int,
        height: Int,
        gender: Boolean,
        age: Int,
        goal: String
    ) {
        viewModelScope.launch {
            val updateUserProfile =
                UpdateUserProfile(name, imageUrl, weight, height, age, gender, goal)
            appRepository.updateUserProfile(updateUserProfile)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}