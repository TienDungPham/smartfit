package com.smartfit.smartfit.ui.signinverify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartfit.smartfit.R
import com.smartfit.smartfit.databinding.FragmentSignInVerifyBinding
import com.smartfit.smartfit.ui.AuthActivity

class SignInVerifyFragment : Fragment() {
    private lateinit var binding: FragmentSignInVerifyBinding

    private lateinit var authActivity: AuthActivity
    private lateinit var imm: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInVerifyBinding.inflate(inflater, container, false)

        binding.inputVerifyCode.addTextChangedListener {
            val verifyCode = binding.inputVerifyCode.text.toString()
            if (verifyCode.length < 6) return@addTextChangedListener
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            authActivity.showLoadingDialog("Signing In. Please wait ...")
            authActivity.verifyCode(verifyCode)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = requireActivity() as AuthActivity
        imm = authActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        authActivity.authViewModel.isVerifySuccess.observe(viewLifecycleOwner) {
            if (it) {
                authActivity.authViewModel.signIn()
            } else {
                authActivity.closeLoadingDialog()
                binding.inputVerifyCode.text.clear()
                binding.inputVerifyCode.error = "Wrong verification code!"
            }
        }
        authActivity.verifyPhoneNumber()
        authActivity.authViewModel.isSignInSuccess.observe(viewLifecycleOwner) {
            authActivity.closeLoadingDialog()
            if (it) {
                authActivity.authSuccess()
            } else {
                findNavController().navigate(R.id.action_nav_sign_in_verify_to_nav_sign_up)
            }
        }
    }
}