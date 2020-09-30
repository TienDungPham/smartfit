package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey
    val id: Long,
    var name: String,
    var imageUrl: String,
    var description: String,
    var courseType: String,
    var level: String,
    var estimatedTime: Int,
    var orderType: String
)