package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.Course

data class CourseDTO(
    val id: Long,
    var name: String,
    var imageUrl: String,
    var description: String,
    var courseType: String,
    var level: String,
    var estimatedTime: Int,
    var orderType: String
) {
    companion object {
        fun mapToCourse(c: CourseDTO): Course {
            return Course(
                id = c.id,
                name = c.name,
                imageUrl = c.imageUrl,
                description = c.description,
                courseType = c.courseType,
                level = c.level,
                estimatedTime = c.estimatedTime,
                orderType = c.orderType
            )
        }
    }
}