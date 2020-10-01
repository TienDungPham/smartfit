package com.smartfit.smartfit.ui.courseclass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.transfer.up.UpdateUserCourse
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CourseClassViewModel(private val appRepository: AppRepository) : ViewModel() {
    val stepDetail: LiveData<CourseStep>
        get() {
            return _stepDetail
        }
    private val _stepDetail = MutableLiveData<CourseStep>()

    fun findCourseDetail(id: Long) {
        viewModelScope.launch {
            _stepDetail.value = appRepository.findStepDetail(id)
        }
    }

    fun updateUserCourse(courseId: Long, stepId: Long) {
        viewModelScope.launch {
            val updateUserCourse = UpdateUserCourse(courseId, stepId)
            appRepository.updateUserCourse(updateUserCourse)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}