package com.smartfit.smartfit.ui.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartfit.smartfit.R
import com.smartfit.smartfit.databinding.FragmentSignInBinding
import com.smartfit.smartfit.ui.AuthActivity

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    private lateinit var authActivity: AuthActivity
    private lateinit var imm: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_country_code,
            resources.getStringArray(R.array.country_code)
        )
        binding.inputCountryCode.adapter = adapter

        binding.sendButton.setOnClickListener {
            val countryCode = binding.inputCountryCode.selectedItem as String
            val phoneNumber = binding.inputPhoneNumber.text.toString()
            if (phoneNumber == "") {
                binding.inputPhoneNumber.error = "Phone number is required!"
                return@setOnClickListener
            }
            authActivity.authViewModel.phoneNumber = "$countryCode$phoneNumber"
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            findNavController().navigate(R.id.action_nav_sign_in_to_nav_sign_in_verify)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = requireActivity() as AuthActivity
        imm = authActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}