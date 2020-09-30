package com.smartfit.smartfit.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.ActivityLauncherBinding
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLauncherBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val launcherViewModel by viewModels<LauncherViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent(this).injectLauncherActivity(this)
    }
}