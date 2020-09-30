package com.smartfit.smartfit.ui.updateaccount

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
import com.smartfit.smartfit.databinding.FragmentUpdateAccountBinding
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

        return binding.root
    }
}