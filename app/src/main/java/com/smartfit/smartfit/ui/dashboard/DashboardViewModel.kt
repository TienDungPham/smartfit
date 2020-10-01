package com.smartfit.smartfit.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DashboardViewModel(private val appRepository: AppRepository) : ViewModel() {
    val userProgress = appRepository.findUserProgress().asLiveData()
    val userCourses = appRepository.findUserCourses().asLiveData()

    fun syncData() {
        viewModelScope.launch {
            appRepository.syncUserProgress()
            appRepository.syncUserCourses()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}