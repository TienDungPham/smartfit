package com.smartfit.smartfit.data.source.remote

import com.smartfit.smartfit.data.transfer.UserAccessDTO
import com.smartfit.smartfit.data.transfer.up.SignIn
import com.smartfit.smartfit.data.transfer.up.SignUp
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("user-auth/sign-in")
    fun signInAsync(@Body signIn: SignIn): Deferred<UserAccessDTO>

    @POST("user-auth/sign-up")
    fun signUpAsync(@Body signUp: SignUp): Deferred<UserAccessDTO>

    @POST("user-auth/refresh-token")
    fun refreshTokenAsync(@Query("refreshToken") refreshToken: String): Deferred<UserAccessDTO>

    @POST("user-auth/sign-out")
    fun signOutAsync(@Query("accessToken") accessToken: String): Deferred<Boolean>
}