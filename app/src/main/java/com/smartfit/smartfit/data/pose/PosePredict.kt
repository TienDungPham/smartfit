package com.smartfit.smartfit.data.pose

data class PosePredict(
    val type: String,
    val name: String,
    val conditions: List<PoseCondition>
) {
    companion object {
        fun sampleData(pose: String): PosePredict {
            return when (pose) {
                "triangle" -> TrianglePose.getPose()
                "reversewarrior" -> ReverseWarrior.getPose()
                else -> TreePose.getPose()
            }
        }
    }
}