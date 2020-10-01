package com.smartfit.smartfit.data.transfer

import androidx.room.PrimaryKey
import com.smartfit.smartfit.data.entity.Meal
import java.util.*

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

        fun mapToMeals(lm: List<MealDTO>): List<Meal> {
            val result = LinkedList<Meal>()
            lm.forEach {
                result.add(
                    Meal(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl,
                        type = it.type,
                        calories = it.calories
                    )
                )
            }
            return result
        }
    }
}