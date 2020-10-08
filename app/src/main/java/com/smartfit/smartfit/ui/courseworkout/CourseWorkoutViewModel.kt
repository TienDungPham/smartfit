package com.smartfit.smartfit.ui.courseworkout

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.data.source.AppRepository
import com.smartfit.smartfit.data.transfer.up.UpdateUserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CourseWorkoutViewModel(private val appRepository: AppRepository) : ViewModel() {
    private val customScope = CoroutineScope(Dispatchers.IO)

    val stepDetail: LiveData<CourseStep>
        get() {
            return _stepDetail
        }
    private val _stepDetail = MutableLiveData<CourseStep>()
    private var currentStepPosition = -1
    private var steps: List<CourseStep>? = null

    fun syncData(courseId: Long) {
        viewModelScope.launch {
            val courseDetail = appRepository.findCourseDetail(courseId)
            val courseSteps =
                courseDetail.steps.filter { s -> s.type == "Exercise" || s.type == "Exercise Count" }
            steps = courseSteps
            nextStep()
        }
    }

    fun nextStep() {
        steps?.let {
            if (currentStepPosition < it.size - 1) {
                currentStepPosition += 1
                _stepDetail.value = it[currentStepPosition]
            }
        }
    }

    fun backStep() {
        steps?.let {
            if (currentStepPosition > 0) {
                currentStepPosition -= 1
                _stepDetail.value = it[currentStepPosition]
            }
        }
    }

    fun getPose(): String {
        return if (stepDetail.value != null) {
            _stepDetail.value!!.pose
        } else {
            ""
        }
    }

    private var countDownTimer: CountDownTimer? = null
    private var countDownTime = 50000L
    val timeLeft: LiveData<Long>
        get() {
            return _timeLeft
        }
    private val _timeLeft = MutableLiveData<Long>(50000L)

    fun startTimer() {
        if (countDownTimer != null) return
        countDownTimer = object : CountDownTimer(countDownTime, 1000) {
            override fun onTick(p0: Long) {
                countDownTime -= 1000
                _timeLeft.value = countDownTime
            }

            override fun onFinish() {
                countDownTime = 50000L
            }
        }.start()
    }

    fun stopTimer() {
        if (countDownTimer == null) return
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun resetTimer() {
        countDownTime = 50000L
        _timeLeft.value = countDownTime
        countDownTimer = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun updateUserSession(courseId: Long) {
        customScope.launch {
            val updateUserSession = UpdateUserSession(courseId)
            appRepository.updateUserSession(updateUserSession)
        }
    }
}