package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAccess(
    @PrimaryKey
    val id: Long = 1,
    var accessToken: String,
    var refreshToken: String,
    var expiryTime: String
)