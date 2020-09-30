package com.smartfit.smartfit.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithSteps(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val steps: List<CourseStep>
)