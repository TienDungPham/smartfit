package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserMeal
import java.util.*

data class UserMealDTO(
    var id: Long,
    var eatenDate: String,
    var totalCalories: Int,
    var servingSize: Int,
    var meal: MealDTO
) {
    companion object {
        fun mapToUserMeal(um: UserMealDTO): UserMeal {
            return UserMeal(
                id = um.id,
                eatenDate = um.eatenDate,
                totalCalories = um.totalCalories,
                servingSize = um.servingSize,
                mealName = um.meal.name,
                mealImageUrl = um.meal.imageUrl,
                mealType = um.meal.type
            )
        }

        fun mapToUserMeals(ums: List<UserMealDTO>): List<UserMeal> {
            val result = LinkedList<UserMeal>()
            ums.forEach {
                result.add(
                    UserMeal(
                        id = it.id,
                        eatenDate = it.eatenDate,
                        totalCalories = it.totalCalories,
                        servingSize = it.servingSize,
                        mealName = it.meal.name,
                        mealImageUrl = it.meal.imageUrl,
                        mealType = it.meal.type
                    )
                )
            }
            return result
        }
    }
}