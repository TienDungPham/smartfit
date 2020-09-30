package com.smartfit.smartfit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    @PrimaryKey
    var id: Long,
    var name: String,
    var imageUrl: String,
    var type: String,
    var calories: Int
)