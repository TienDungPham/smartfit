package com.smartfit.smartfit.data.transfer

data class CourseStepDTO(
    val id: Long,
    var name: String,
    var description: String,
    var type: String,
    var videoUrl: String,
    var pose: String
)