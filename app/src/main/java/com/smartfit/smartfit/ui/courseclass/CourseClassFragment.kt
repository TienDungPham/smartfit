package com.smartfit.smartfit.ui.courseclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCourseClassBinding
import javax.inject.Inject

class CourseClassFragment : Fragment() {
    private lateinit var binding: FragmentCourseClassBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val courseClassViewModel by viewModels<CourseClassViewModel> {
        viewModelFactory
    }
    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseClassBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCourseClassFragment(this)

        courseClassViewModel.stepDetail.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.stepTitle.text = it.name
            binding.stepVideo.player = player

            val mediaItem = MediaItem.fromUri(it.videoUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()

            binding.stepDescription.loadUrl("https://smartfitapi2.herokuapp.com/api/v1/ui/practice?description=${it.description}")

            arguments?.getLong("courseId").let { cid ->
                courseClassViewModel.updateUserCourse(cid!!, it.id)
            }
        }

        arguments?.getLong("stepId").let {
            courseClassViewModel.findStepDetail(it!!)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.stop()
    }
}