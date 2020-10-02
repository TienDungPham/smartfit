package com.smartfit.smartfit.ui.courseworkout

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseLandmark
import com.smartfit.smartfit.data.pose.PosePredict
import kotlin.math.atan2

class PoseAnalyzer(
    private val poseDetector: PoseDetector,
    private val messageCallback: (String) -> Unit
) : ImageAnalysis.Analyzer {
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            poseDetector.process(image)
                .addOnSuccessListener {
                    if (it.allPoseLandmarks.isNotEmpty()) {
                        val posePredict = PosePredict.sampleData()
                        for (condition in posePredict.conditions) {
                            val cl1 = it.getPoseLandmark(condition.cl1)
                            val cl2 = it.getPoseLandmark(condition.cl2)
                            val cl3 = it.getPoseLandmark(condition.cl3)
                            if (cl1 != null && cl2 != null && cl3 != null) {
                                val angle = getAngle(cl1, cl2, cl3)
                                if (angle > condition.angleRangeTo) {
                                    messageCallback(condition.greaterThanMessage)
                                    break;
                                } else if (angle < condition.angleRangeFrom) {
                                    messageCallback(condition.smallThanMessage)
                                    break;
                                } else {
                                    messageCallback("Very good!")
                                }
                            } else {
                                messageCallback("")
                            }
                        }
                    }
                }
                .addOnFailureListener {
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    private fun getAngle(
        firstPoint: PoseLandmark,
        midPoint: PoseLandmark,
        lastPoint: PoseLandmark
    ): Double {
        var result = Math.toDegrees(
            (atan2(
                lastPoint.getPosition().y - midPoint.getPosition().y,
                lastPoint.getPosition().x - midPoint.getPosition().x
            )
                    - atan2(
                firstPoint.getPosition().y - midPoint.getPosition().y,
                firstPoint.getPosition().x - midPoint.getPosition().x
            )).toDouble()
        )
        result = Math.abs(result)
        if (result > 180) {
            result = 360.0 - result
        }
        return result
    }
}