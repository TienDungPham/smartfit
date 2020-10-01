package com.smartfit.smartfit.ui.updateaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentUpdateAccountBinding
import com.smartfit.smartfit.utils.Utils
import javax.inject.Inject

class UpdateAccountFragment : Fragment() {
    private lateinit var binding: FragmentUpdateAccountBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val updateAccountViewModel by viewModels<UpdateAccountViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateAccountBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectUpdateAccountFragment(this)

        binding.includeUpdateAccount.doneButton.setOnClickListener {
            // TODO: Update user profile
        }

        updateAccountViewModel.userProfile.observe(viewLifecycleOwner) {
            if (it == null) return@observe
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

                inputName.setText(it.name)
                inputWeight.setText(it.weight.toString())
                inputHeight.setText(it.height.toString())
                inputGender.setText(if (it.gender) "Female" else "Male")
                inputAge.setText(it.age.toString())
                inputGoal.setText(it.goal)
            }

            Glide.with(binding.accountImage)
                .load(it.imageUrl)
                .circleCrop()
                .error(R.drawable.sample_profile)
                .into(binding.accountImage)
        }
        updateAccountViewModel.syncUserProfile()

        return binding.root
    }
}