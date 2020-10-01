package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProgress(
    @PrimaryKey
    val id: Long = 1,
    var calories: Int,
    var minutes: String,
    var workouts: Int,
    var goal: Int
)