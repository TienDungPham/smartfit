package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseStep(
    @PrimaryKey
    val id: Long,
    var name: String,
    var description: String,
    var type: String,
    var videoUrl: String,
    var pose: String,
    val courseId: Long
)