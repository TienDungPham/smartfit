package com.smartfit.smartfit.data.source.remote

import com.smartfit.smartfit.data.transfer.MealDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MealService {
    @GET("meal/all")
    fun findAllMealsAsync(): Deferred<List<MealDTO>>
}