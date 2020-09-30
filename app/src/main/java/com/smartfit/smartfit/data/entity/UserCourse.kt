package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserCourse(
    @PrimaryKey
    val id: Long,
    var courseStep: Int,
    var courseProgress: Int,
    val courseId: Long,
    val courseName: String,
    val courseImageUrl: String
)