package com.smartfit.smartfit.data.source.remote

import com.smartfit.smartfit.data.transfer.*
import com.smartfit.smartfit.data.transfer.up.UpdateUserCourse
import com.smartfit.smartfit.data.transfer.up.UpdateUserMeal
import com.smartfit.smartfit.data.transfer.up.UpdateUserProfile
import com.smartfit.smartfit.data.transfer.up.UpdateUserSession
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InfoService {
    @GET("user-info/profile")
    fun findUserProfileAsync(@Query("accessToken") accessToken: String): Deferred<UserProfileDTO>

    @POST("user-info/profile")
    fun updateUserProfileAsync(
        @Query("accessToken") accessToken: String,
        @Body updateUserProfile: UpdateUserProfile
    ): Deferred<Boolean>

    @GET("user-info/user-course")
    fun findUserCoursesAsync(@Query("accessToken") accessToken: String): Deferred<List<UserCourseDTO>>

    @POST("user-info/update-user-course")
    fun updateUserCourseAsync(
        @Query("accessToken") accessToken: String,
        @Body updateUserCourse: UpdateUserCourse
    ): Deferred<Boolean>

    @POST("user-info/update-user-session")
    fun updateUserSessionAsync(
        @Query("accessToken") accessToken: String,
        @Body updateUserSession: UpdateUserSession
    ): Deferred<Boolean>

    @GET("user-info/order")
    fun findUserOrderAsync(@Query("accessToken") accessToken: String): Deferred<UserOrderDTO>

    @GET("user-info/user-progress")
    fun findUserProgressAsync(
        @Query("accessToken") accessToken: String
    ): Deferred<UserProgressDTO>

    @GET("user-info/user-meal")
    fun findUserMealAsync(@Query("accessToken") accessToken: String): Deferred<List<UserMealDTO>>

    @POST("user-info/update-user-meal")
    fun updateUserMealAsync(
        @Query("accessToken") accessToken: String,
        @Body updateUserMeal: UpdateUserMeal
    ): Deferred<Boolean>
}