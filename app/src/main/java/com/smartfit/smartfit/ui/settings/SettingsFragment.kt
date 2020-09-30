package com.smartfit.smartfit.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentSettingsBinding
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val settingsViewModel by viewModels<SettingsViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectSettingsFragment(this)

        return binding.root
    }
}