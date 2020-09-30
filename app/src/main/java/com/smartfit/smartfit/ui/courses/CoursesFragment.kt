package com.smartfit.smartfit.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }
}