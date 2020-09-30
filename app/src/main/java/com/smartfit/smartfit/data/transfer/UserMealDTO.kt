package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserMeal

data class UserMealDTO(
    var id: Long,
    var eatenDate: String,
    var totalCalories: Int,
    var meal: MealDTO
) {
    companion object {
        fun mapToUserMeal(um: UserMealDTO): UserMeal {
            return UserMeal(
                id = um.id,
                eatenDate = um.eatenDate,
                totalCalories = um.totalCalories,
                mealName = um.meal.name,
                mealImageUrl = um.meal.imageUrl,
                mealType = um.meal.type
            )
        }
    }
}