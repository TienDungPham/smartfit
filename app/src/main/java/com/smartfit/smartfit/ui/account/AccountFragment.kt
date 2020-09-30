package com.smartfit.smartfit.ui.account

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
import com.smartfit.smartfit.databinding.FragmentAccountBinding
import javax.inject.Inject

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val accountViewModel by viewModels<AccountViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectAccountFragment(this)

        return binding.root
    }
}