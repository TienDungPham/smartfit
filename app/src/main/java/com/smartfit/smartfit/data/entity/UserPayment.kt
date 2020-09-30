package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPayment(
    @PrimaryKey
    val id: Long,
    var type: String,
    var status: String,
    var description: String,
    var totalPrice: Float,
    var orderId: Long
)