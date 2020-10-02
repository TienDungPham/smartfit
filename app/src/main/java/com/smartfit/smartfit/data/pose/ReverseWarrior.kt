package com.smartfit.smartfit.data.pose

import com.google.mlkit.vision.pose.PoseLandmark
import java.util.*

object ReverseWarrior {
    fun getPose(): PosePredict {
        val conditions = LinkedList<PoseCondition>()
        conditions.add(
            PoseCondition(
                PoseLandmark.Type.RIGHT_SHOULDER,
                PoseLandmark.Type.RIGHT_ELBOW,
                PoseLandmark.Type.RIGHT_WRIST,
                90.0,
                180.0,
                "Straight your right hand",
                "Straight your right hand"
            )
        )
        conditions.add(
            PoseCondition(
                PoseLandmark.Type.LEFT_SHOULDER,
                PoseLandmark.Type.LEFT_ELBOW,
                PoseLandmark.Type.LEFT_WRIST,
                90.0,
                180.0,
                "Straight your left hand",
                "Straight your left hand"
            )
        )
        conditions.add(
            PoseCondition(
                PoseLandmark.Type.RIGHT_HIP,
                PoseLandmark.Type.RIGHT_KNEE,
                PoseLandmark.Type.RIGHT_ANKLE,
                60.0,
                160.0,
                "Lower your hip and bend your right leg 80 degree",
                "Lower your hip and bend your right leg 80 degree"
            )
        )
        conditions.add(
            PoseCondition(
                PoseLandmark.Type.RIGHT_SHOULDER,
                PoseLandmark.Type.RIGHT_HIP,
                PoseLandmark.Type.RIGHT_KNEE,
                60.0,
                180.0,
                "Higher your right hand while looking at it",
                "Higher your right hand while looking at it"
            )
        )
        return PosePredict("Normal", "reversewarrior", conditions)
    }
}