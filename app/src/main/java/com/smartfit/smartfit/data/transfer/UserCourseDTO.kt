package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.data.entity.UserCourse

data class UserCourseDTO(
    val id: Long,
    var courseStep: Int,
    var courseProgress: Int,
    var course: CourseDTO
) {
    companion object {
        fun mapToUserCourse(uc: UserCourseDTO): UserCourse {
            return UserCourse(
                id = uc.id,
                courseStep = uc.courseStep,
                courseProgress = uc.courseProgress,
                courseId = uc.course.id,
                courseName = uc.course.name,
                courseImageUrl = uc.course.imageUrl
            )
        }

        fun mapToCourse(uc: UserCourseDTO): Course {
            return Course(
                id = uc.course.id,
                name = uc.course.name,
                imageUrl = uc.course.imageUrl,
                description = uc.course.description,
                courseType = uc.course.courseType,
                level = uc.course.level,
                estimatedTime = uc.course.estimatedTime,
                orderType = uc.course.orderType
            )
        }
    }
}