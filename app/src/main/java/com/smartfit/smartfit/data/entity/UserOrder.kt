package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserOrder(
    @PrimaryKey
    val id: Long = 1,
    var type: String,
    var status: String,
    var expiryTime: String
)