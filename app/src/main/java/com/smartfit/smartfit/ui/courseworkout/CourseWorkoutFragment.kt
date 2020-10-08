package com.smartfit.smartfit.ui.courseworkout

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetectorOptions
import com.smartfit.smartfit.R
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
    private val poseAnalyzer = PoseAnalyzer(poseDetector, {
        if (courseWorkoutViewModel.stepDetail.value != null) {
            courseWorkoutViewModel.stepDetail.value!!.pose
        } else {
            ""
        }
    }) {
        if (isPredicting) {
            binding.feedbackMessage.text = it
            if (it == "Very good!") {
                courseWorkoutViewModel.startTimer()
            } else {
                courseWorkoutViewModel.stopTimer()
            }
        } else {
            courseWorkoutViewModel.startTimer()
        }
    }

    private var isFrontCamera = true
    private var isPredicting = false
    private var vibratorService: Vibrator? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        vibratorService = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.notify_effect)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseWorkoutBinding.inflate(inflater, container, false)
        appComponent(requireContext()).injectCourseWorkoutFragment(this)
        arguments?.getLong("courseId")?.let { ci ->
            courseWorkoutViewModel.updateUserSession(ci)
        }

        if (checkCameraPermission()) requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
        else startCamera()

        binding.apply {
            backImage.isClickable = false

            backImage.setOnClickListener {
                findNavController().navigateUp()
            }

            stepName.setOnClickListener {
                val stepDetail = courseWorkoutViewModel.stepDetail
                if (stepDetail.value == null) return@setOnClickListener
                arguments?.getLong("courseId")?.let { ci ->
                    val bundle = Bundle()
                    bundle.putLong("courseId", ci)
                    bundle.putLong("stepId", stepDetail.value!!.id)
                    findNavController().navigate(
                        R.id.action_nav_course_workout_to_nav_course_class,
                        bundle
                    )
                }
            }

            flipCamera.setOnClickListener {
                isFrontCamera = !isFrontCamera
                startCamera()
            }
        }

        courseWorkoutViewModel.stepDetail.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.apply {
                backImage.isClickable = true
            }
            binding.stepName.text = it.name

            Glide.with(binding.stepImage)
                .asGif()
                .load(it.videoUrl)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.stepImage)
        }

        courseWorkoutViewModel.timeLeft.observe(viewLifecycleOwner) {
            when (it) {
                in 45000L..50000L -> {
                    binding.feedbackMessage.text = "Are your ready ?"
                }
                in 40000L..45000L -> {
                    binding.feedbackMessage.text = "${(it - 40000) / 1000}"
                    vibratorService?.vibrate(500)
                    mediaPlayer?.start()
                }
                else -> {
                    isPredicting = true
                }
            }
            if (isPredicting) {
                binding.timer.text = "${it / 1000}S"
            }
            if (it == 0L) {
                courseWorkoutViewModel.resetTimer()
                courseWorkoutViewModel.nextStep()
                isPredicting = false
            }
        }

        arguments?.getLong("courseId")?.let { ci ->
            courseWorkoutViewModel.syncData(ci)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        arguments?.getLong("courseId")?.let { ci ->
            courseWorkoutViewModel.updateUserSession(ci)
        }
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
                val cameraSelector =
                    if (isFrontCamera) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA

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