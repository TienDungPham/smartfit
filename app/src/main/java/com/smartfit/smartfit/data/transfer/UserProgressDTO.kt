package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserProgress

data class UserProgressDTO(
    var calories: Int,
    var minutes: String,
    var workouts: Int
) {
    companion object {
        fun mapToUserProgress(up: UserProgressDTO): UserProgress {
            return UserProgress(
                calories = up.calories,
                minutes = up.minutes,
                workouts = up.workouts
            )
        }
    }
}