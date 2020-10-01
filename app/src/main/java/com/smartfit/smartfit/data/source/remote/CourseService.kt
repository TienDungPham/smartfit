package com.smartfit.smartfit.data.source.remote

import com.smartfit.smartfit.data.transfer.CourseDTO
import com.smartfit.smartfit.data.transfer.CourseDetailDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseService {
    @GET("course/all")
    fun findAllCourseAsync(): Deferred<List<CourseDTO>>

    @GET("course/detail")
    fun findCourseDetailAsync(@Query("id") id: Int): Deferred<CourseDetailDTO>

    @GET("course/access-check")
    fun checkUserAccessAsync(
        @Query("id") id: Int,
        @Query("accessToken") accessToken: String
    ): Deferred<Boolean>
}