package com.smartfit.smartfit.data.source.local

import androidx.room.*
import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.data.entity.CourseStep
import com.smartfit.smartfit.data.entity.CourseWithSteps
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM Course")
    fun findAllCourses(): Flow<List<Course>>

    @Transaction
    @Query("SELECT * FROM Course WHERE id = :id LIMIT 1")
    fun findCourseDetail(id: Long): CourseWithSteps

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCourses(courses: List<Course>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCourseSteps(courseSteps: List<CourseStep>)

    @Query("SELECT * FROM CourseStep WHERE id = :id")
    fun findStepDetail(id: Long): CourseStep
}