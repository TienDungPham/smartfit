package com.smartfit.smartfit.ui.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoursesViewModel(private val appRepository: AppRepository) : ViewModel() {
    val courses = appRepository.findAllCourses().asLiveData()

    fun syncCourse() {
        viewModelScope.launch {
            appRepository.syncAllCourses()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}