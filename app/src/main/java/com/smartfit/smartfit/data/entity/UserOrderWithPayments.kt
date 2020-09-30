package com.smartfit.smartfit.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserOrderWithPayments(
    @Embedded
    val order: UserOrder,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val payments: List<UserPayment>
)