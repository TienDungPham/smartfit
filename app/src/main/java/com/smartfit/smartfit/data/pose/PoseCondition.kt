package com.smartfit.smartfit.data.pose

import com.google.mlkit.vision.pose.PoseLandmark

data class PoseCondition(
    val cl1: PoseLandmark.Type,
    val cl2: PoseLandmark.Type,
    val cl3: PoseLandmark.Type,
    val angleRangeFrom: Double,
    val angleRangeTo: Double,
    val greaterThanMessage: String,
    val smallThanMessage: String
)