package com.smartfit.smartfit.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smartfit.smartfit.data.entity.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM Meal")
    fun findAllMeals(): Flow<Meal>

    @Insert
    fun saveMeals(meals: List<Meal>)
}