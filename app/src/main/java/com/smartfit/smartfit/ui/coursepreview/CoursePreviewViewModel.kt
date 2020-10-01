package com.smartfit.smartfit.ui.coursepreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.entity.CourseWithSteps
import com.smartfit.smartfit.data.source.AppRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoursePreviewViewModel(private val appRepository: AppRepository) : ViewModel() {
    val courseDetail: LiveData<CourseWithSteps>
        get() {
            return _courseDetail
        }
    private val _courseDetail = MutableLiveData<CourseWithSteps>()

    val isUserAllow: LiveData<Boolean>
        get() {
            return _isUserAllow
        }
    private val _isUserAllow = MutableLiveData<Boolean>()

    fun findCourseDetail(id: Long) {
        viewModelScope.launch {
            _courseDetail.value = appRepository.findCourseDetail(id)
            appRepository.syncCourseDetail(id)
            _courseDetail.value = appRepository.findCourseDetail(id)
        }
    }

    fun checkUserAccess(id: Long) {
        viewModelScope.launch {
            _isUserAllow.value = appRepository.checkUserAccess(id)
        }
    }

    fun resetUserAccess() {
        _isUserAllow.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}