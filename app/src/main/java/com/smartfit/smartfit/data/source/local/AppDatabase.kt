package com.smartfit.smartfit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartfit.smartfit.data.entity.*

@Database(
    entities = [
        Course::class,
        CourseStep::class,
        Meal::class,
        UserAccess::class,
        UserCourse::class,
        UserMeal::class,
        UserOrder::class,
        UserPayment::class,
        UserProfile::class,
        UserProgress::class],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun mealDao(): MealDao
    abstract fun userDao(): UserDao
}