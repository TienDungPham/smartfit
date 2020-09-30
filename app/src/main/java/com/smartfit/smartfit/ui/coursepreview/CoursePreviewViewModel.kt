package com.smartfit.smartfit.ui.coursepreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel

class CoursePreviewViewModel(private val appRepository: AppRepository) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}