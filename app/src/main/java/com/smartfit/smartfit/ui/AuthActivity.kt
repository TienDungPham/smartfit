package com.smartfit.smartfit.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.ActivityAuthBinding
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val authViewModel by viewModels<AuthViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent(this).injectAuthActivity(this)
    }
}