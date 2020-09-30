package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey
    val id: Long = 1,
    var name: String,
    var imageUrl: String,
    var weight: Int,
    var height: Int,
    var age: Int,
    var gender: Boolean,
    var goal: String
)