package com.smartfit.smartfit.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.data.entity.CourseWithSteps
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    fun findAllCourses(): Flow<Course>

    @Transaction
    @Query("SELECT * FROM Course WHERE id = :id LIMIT 1")
    fun findCourseDetail(id: Long): CourseWithSteps

    @Insert
    fun saveCourse(course: Course)

    @Insert
    fun saveCourseSteps(courseSteps: List<CourseStep>)
}