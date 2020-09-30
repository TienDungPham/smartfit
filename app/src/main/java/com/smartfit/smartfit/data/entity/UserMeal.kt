package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserMeal(
    @PrimaryKey
    var id: Long,
    var eatenDate: String,
    var totalCalories: Int,
    var mealName: String,
    var mealImageUrl: String,
    var mealType: String
)
