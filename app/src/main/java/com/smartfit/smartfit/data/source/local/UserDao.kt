package com.smartfit.smartfit.data.source.local

import androidx.room.*
import com.smartfit.smartfit.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM UserAccess LIMIT 1")
    suspend fun findUserAccess(): UserAccess

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserAccess(userAccess: UserAccess)

    @Delete
    fun deleteUserAccess(userAccess: UserAccess)

    @Query("SELECT * FROM UserProfile LIMIT 1")
    fun findUserProfile(): Flow<UserProfile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserProfile(userProfile: UserProfile)

    @Transaction
    @Query("SELECT * FROM UserOrder LIMIT 1")
    fun findUserOrder(): Flow<UserOrderWithPayments>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserOrder(userOrder: UserOrder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserPayments(userPayments: List<UserPayment>)

    @Query("SELECT * FROM UserProgress LIMIT 1")
    fun findUserProgress(): Flow<UserProgress>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserProgress(userProgress: UserProgress)

    @Query("SELECT * FROM UserCourse")
    fun findUserCourses(): Flow<List<UserCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserCourses(userCourses: List<UserCourse>)

    @Query("SELECT * FROM UserMeal")
    fun findUserMeals(): Flow<List<UserMeal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserMeal(userMeal: UserMeal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserMeals(userMeals: List<UserMeal>)
}