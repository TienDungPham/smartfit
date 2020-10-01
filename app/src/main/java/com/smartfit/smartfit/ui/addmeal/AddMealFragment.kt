package com.smartfit.smartfit.ui.addmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.data.entity.Meal
import com.smartfit.smartfit.databinding.FragmentAddMealBinding
import com.smartfit.smartfit.ui.MainActivity
import java.util.*
import javax.inject.Inject

class AddMealFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddMealBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val addMealViewModel by viewModels<AddMealViewModel> {
        viewModelFactory
    }

    private var _meals = LinkedList<Meal>()
    private var meals = LinkedList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMealBinding.inflate(inflater, container, false)
        appComponent(requireContext()).injectAddMealFragment(this)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            meals
        )
        binding.inputMeal.setAdapter(adapter)
        binding.inputMeal.showSoftInputOnFocus = false

        binding.dismissButton.setOnClickListener {
            binding.inputMeal.text.clear()
            binding.inputMealSize.text?.clear()
        }

        binding.addButton.setOnClickListener {
            val mealName = binding.inputMeal.text.toString()
            val mealSize = binding.inputMealSize.text.toString()
            if (mealName == "") {
                binding.inputMeal.error = "Required"
                return@setOnClickListener
            }
            if (mealSize == "") {
                binding.inputMealSize.error = "Required"
                return@setOnClickListener
            }
            try {
                val selectedMeal = _meals.first {
                    it.name == mealName
                }
                addMealViewModel.updateUserMeal(selectedMeal.id, mealSize.toLong())
                (requireActivity() as MainActivity).showLoadingDialog("Updating....")
            } catch (e: Exception) {
            }
        }

        addMealViewModel.meals.observe(viewLifecycleOwner) {
            _meals.clear()
            meals.clear()
            it.forEach { m ->
                _meals.add(m)
                meals.add(m.name)
            }
            adapter.notifyDataSetChanged()
        }

        addMealViewModel.isUpdateSuccess.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).closeLoadingDialog()
            dismiss()
        }

        addMealViewModel.syncMeals()

        return binding.root
    }
}