package com.smartfit.smartfit.data.transfer

data class UserPaymentDTO(
    val id: Long,
    var type: String,
    var status: String,
    var description: String,
    var totalPrice: Float
)