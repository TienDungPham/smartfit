package com.smartfit.smartfit.ui.updateaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentUpdateAccountBinding

class UpdateAccountFragment : Fragment() {
    private lateinit var binding: FragmentUpdateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
}