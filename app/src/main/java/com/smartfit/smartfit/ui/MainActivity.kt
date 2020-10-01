package com.smartfit.smartfit.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.ActivityMainBinding
import com.smartfit.smartfit.ui.addmeal.AddMealFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    private lateinit var loadingDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent(this).injectMainActivity(this)
        loadingDialog = ProgressDialog(this)

        val navController = findNavController(R.id.main_host_fragment)
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.nav_courses -> {
                    navController.navigate(R.id.nav_courses)
                    hideBottomAppBar()
                    true
                }
                R.id.nav_meal -> {
                    navController.navigate(R.id.nav_meal)
                    hideBottomAppBar()
                    true
                }
                R.id.nav_account -> {
                    navController.navigate(R.id.nav_account)
                    hideBottomAppBar()
                    true
                }
                else -> false
            }
        }
        binding.addMeal.setOnClickListener {
            val addMealFragment = AddMealFragment()
            addMealFragment.show(supportFragmentManager, "ModalBottomSheet")
        }
    }

    fun hideBottomAppBar() {
        binding.bottomAppBar.visibility = View.GONE
        binding.addMeal.visibility = View.GONE
    }

    fun showBottomAppBar() {
        binding.bottomAppBar.visibility = View.VISIBLE
        binding.addMeal.visibility = View.VISIBLE
    }

    fun showLoadingDialog(message: String) {
        loadingDialog.apply {
            setMessage(message)
            setCancelable(true)
            show()
        }
    }

    fun closeLoadingDialog() {
        loadingDialog.dismiss()
    }
}