package com.smartfit.smartfit.data.transfer

import androidx.room.PrimaryKey
import com.smartfit.smartfit.data.entity.Meal

data class MealDTO(
    @PrimaryKey
    var id: Long,
    var name: String,
    var imageUrl: String,
    var type: String,
    var calories: Int
) {
    companion object {
        fun mapToMeal(m: MealDTO): Meal {
            return Meal(
                id = m.id,
                name = m.name,
                imageUrl = m.imageUrl,
                type = m.type,
                calories = m.calories
            )
        }
    }
}