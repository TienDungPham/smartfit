package com.smartfit.smartfit.data.source.remote

import com.smartfit.smartfit.data.transfer.MealDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("meal/all")
    fun findAllMealsAsync(@Query("accessToken") accessToken: String): Deferred<MealDTO>
}