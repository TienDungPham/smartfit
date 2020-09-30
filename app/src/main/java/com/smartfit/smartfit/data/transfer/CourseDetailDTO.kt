package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.Course
import com.smartfit.smartfit.data.entity.CourseStep
import java.util.*

data class CourseDetailDTO(
    val id: Long,
    var name: String,
    var imageUrl: String,
    var description: String,
    var courseType: String,
    var level: String,
    var estimatedTime: Int,
    var orderType: String,
    var courseSteps: List<CourseStepDTO>
) {
    companion object {
        fun mapToCourse(cd: CourseDetailDTO): Course {
            return Course(
                id = cd.id,
                name = cd.name,
                imageUrl = cd.imageUrl,
                description = cd.description,
                courseType = cd.courseType,
                level = cd.level,
                estimatedTime = cd.estimatedTime,
                orderType = cd.orderType
            )
        }

        fun mapToCourseSteps(cd: CourseDetailDTO): List<CourseStep> {
            val results = LinkedList<CourseStep>()
            cd.courseSteps.forEach {
                val result = CourseStep(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    type = it.type,
                    videoUrl = it.videoUrl,
                    pose = it.pose,
                    courseId = cd.id
                )
                results.add(result)
            }
            return results
        }
    }
}