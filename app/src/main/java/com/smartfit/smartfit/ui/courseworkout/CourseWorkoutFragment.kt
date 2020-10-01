package com.smartfit.smartfit.ui.courseworkout

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetectorOptions
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCourseWorkoutBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject


class CourseWorkoutFragment : Fragment() {
    private lateinit var binding: FragmentCourseWorkoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val courseWorkoutViewModel by viewModels<CourseWorkoutViewModel> {
        viewModelFactory
    }

    private val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_FAST)
        .build()
    private val poseDetector = PoseDetection.getClient(options)
    private lateinit var cameraExecutor: ExecutorService
    private val poseAnalyzer = PoseAnalyzer(poseDetector) {
        binding.poseMessage.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseWorkoutBinding.inflate(inflater, container, false)
        appComponent(requireContext()).injectCourseWorkoutFragment(this)

        if (checkCameraPermission()) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
        } else {
            startCamera()
        }

        binding.apply {
            backImage.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    private fun checkCameraPermission(): Boolean {
        return checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                findNavController().navigateUp()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            Runnable {
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
                val imageCapture = ImageCapture.Builder().build()
                val imageAnalyzer = ImageAnalysis.Builder()
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, poseAnalyzer)
                    }
                val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture, imageAnalyzer
                    )
                } catch (exc: Exception) {
                }
            },
            ContextCompat.getMainExecutor(requireContext())
        )
    }
}