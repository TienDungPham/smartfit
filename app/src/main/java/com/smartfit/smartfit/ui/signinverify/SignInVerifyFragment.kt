package com.smartfit.smartfit.ui.signinverify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentSignInVerifyBinding

class SignInVerifyFragment : Fragment() {
    private lateinit var binding: FragmentSignInVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInVerifyBinding.inflate(inflater, container, false)

        return binding.root
    }
}