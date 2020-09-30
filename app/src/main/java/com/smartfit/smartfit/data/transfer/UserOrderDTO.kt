package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserOrder
import com.smartfit.smartfit.data.entity.UserPayment
import java.util.*

data class UserOrderDTO(
    var type: String,
    var status: String,
    var expiryTime: String,
    var userPayments: List<UserPaymentDTO>
) {
    companion object {
        fun mapToUserOrder(uo: UserOrderDTO): UserOrder {
            return UserOrder(
                id = 1,
                type = uo.type,
                status = uo.status,
                expiryTime = uo.expiryTime
            )
        }

        fun mapToUserPayments(uo: UserOrderDTO): List<UserPayment> {
            val results = LinkedList<UserPayment>()
            uo.userPayments.forEach {
                val result = UserPayment(
                    id = it.id,
                    type = it.type,
                    status = it.status,
                    description = it.description,
                    totalPrice = it.totalPrice,
                    orderId = 1
                )
                results.add(result)
            }
            return results
        }
    }
}