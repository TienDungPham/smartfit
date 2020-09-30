package com.smartfit.smartfit.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.databinding.FragmentSignUpBinding
import com.smartfit.smartfit.ui.AuthActivity
import com.smartfit.smartfit.utils.Utils

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private lateinit var authActivity: AuthActivity
    private lateinit var imm: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.includeUpdateAccount.apply {
            inputGender.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    Utils.getGenders()
                )
            )
            inputGoal.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    Utils.getGoals()
                )
            )
            doneButton.setOnClickListener {
                val name = inputName.text.toString()
                val weight = inputWeight.text.toString()
                val height = inputHeight.text.toString()
                val age = inputAge.text.toString()
                val gender = inputGender.text.toString()
                val goal = inputGoal.text.toString()

                if (name == "") {
                    inputName.error = "Name is required!"
                    return@setOnClickListener
                }
                if (weight == "") {
                    inputWeight.error = "Weight is required!"
                    return@setOnClickListener
                }
                if (height == "") {
                    inputHeight.error = "Height is required!"
                    return@setOnClickListener
                }
                if (age == "") {
                    inputAge.error = "Age is required!"
                    return@setOnClickListener
                }
                if (gender == "") {
                    inputGender.error = "Gender is required!"
                    return@setOnClickListener
                }
                if (goal == "") {
                    inputGoal.error = "Goal is required!"
                    return@setOnClickListener
                }
                authActivity.showLoadingDialog("Signing Up. Please wait ...")

                authActivity.authViewModel.signUp(
                    name,
                    weight.toInt(),
                    height.toInt(),
                    age.toInt(),
                    gender == "Female",
                    goal
                )
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authActivity = (requireActivity() as AuthActivity)
        imm = authActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        authActivity.authViewModel.isSignUpSuccess.observe(viewLifecycleOwner) {
            if (it) {
                authActivity.closeLoadingDialog()
                authActivity.authSuccess()
            }
        }
    }
}