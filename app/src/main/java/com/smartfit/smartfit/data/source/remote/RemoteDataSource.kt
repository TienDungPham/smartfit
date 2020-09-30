package com.smartfit.smartfit.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.smartfit.smartfit.data.transfer.UserAccessDTO
import com.smartfit.smartfit.data.transfer.up.SignIn
import com.smartfit.smartfit.data.transfer.up.SignUp
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://smartfitapi2.herokuapp.com/api/v1/")
        .build()

    private val authService by lazy { retrofit.create(AuthService::class.java) }
    private val courseService by lazy { retrofit.create(CourseService::class.java) }
    private val infoService by lazy { retrofit.create(InfoService::class.java) }
    private val mealService by lazy { retrofit.create(MealService::class.java) }

    suspend fun refreshToken(refreshToken: String): UserAccessDTO =
        authService.refreshTokenAsync(refreshToken).await()

    suspend fun signIn(signIn: SignIn): UserAccessDTO =
        authService.signInAsync(signIn).await()

    suspend fun signUp(signUp: SignUp): UserAccessDTO =
        authService.signUpAsync(signUp).await()
}