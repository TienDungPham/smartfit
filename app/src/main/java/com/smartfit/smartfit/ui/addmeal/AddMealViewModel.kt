package com.smartfit.smartfit.ui.addmeal

import androidx.lifecycle.*
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.transfer.up.UpdateUserMeal
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AddMealViewModel(private val appRepository: AppRepository) : ViewModel() {
    val meals = appRepository.findAllMeals().asLiveData()
    val isUpdateSuccess: LiveData<Boolean>
        get() {
            return _isUpdateSuccess
        }
    private val _isUpdateSuccess = MutableLiveData<Boolean>()

    fun syncMeals() {
        viewModelScope.launch {
            appRepository.syncMeals()
        }
    }

    fun updateUserMeal(mealId: Long, servingSize: Long) {
        viewModelScope.launch {
            val updateUserMeal = UpdateUserMeal(mealId, servingSize)
            appRepository.updateUserMeal(updateUserMeal)
            _isUpdateSuccess.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}