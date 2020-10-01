package com.smartfit.smartfit.ui.usermeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentUserMealBinding
import javax.inject.Inject
import kotlin.math.roundToInt

class UserMealFragment : Fragment() {
    private lateinit var binding: FragmentUserMealBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val userMealViewModel by viewModels<UserMealViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserMealBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectUserMealFragment(this)

        val adapter = UserMealAdapter()
        binding.paymentRv.layoutManager = LinearLayoutManager(requireContext())
        binding.paymentRv.adapter = adapter

        userMealViewModel.userMeals.observe(viewLifecycleOwner) {
            if (it == null || it.isEmpty()) return@observe
            adapter.submitList(it)
            val carbsMeals = it.filter { um ->
                um.mealType == "Carbs"
            }
            val carbsMealsPercent =
                (carbsMeals.size.toFloat() / it.size.toFloat() * 100F).roundToInt()

            val proteinMeals = it.filter { um ->
                um.mealType == "Protein"
            }
            val proteinMealsPercent =
                (proteinMeals.size.toFloat() / it.size.toFloat() * 100F).roundToInt()

            val fatMeals = it.filter { um ->
                um.mealType == "Fat"
            }
            val fatMealsPercent =
                (fatMeals.size.toFloat() / it.size.toFloat() * 100F).roundToInt()

            binding.apply {
                carbsBar.progress = carbsMealsPercent
                carbsPercent.text = "$carbsMealsPercent%"

                proteinBar.progress = proteinMealsPercent
                proteinPercent.text = "$proteinMealsPercent%"

                fatBar.progress = fatMealsPercent
                fatPercent.text = "$fatMealsPercent%"
            }
        }

        userMealViewModel.syncUserMeals()

        return binding.root
    }
}