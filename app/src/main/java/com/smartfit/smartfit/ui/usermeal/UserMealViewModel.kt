package com.smartfit.smartfit.ui.usermeal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UserMealViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userMeals = appRepository.findUserMeal().asLiveData()

    fun syncUserMeals() {
        viewModelScope.launch {
            appRepository.syncUserMeals()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}