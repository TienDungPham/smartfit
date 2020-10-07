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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.smartfit.smartfit.R
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.data.ActionItem
import com.smartfit.smartfit.databinding.FragmentAccountBinding
import com.smartfit.smartfit.ui.MainActivity
import java.util.*
import javax.inject.Inject
import kotlin.math.sqrt

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

        val settingsAdapter = SettingsAdapter {
            when (it) {
                0 -> {
                    findNavController().navigate(R.id.action_nav_account_to_nav_update_account)
                }
                1 -> {
                    findNavController().navigate(R.id.action_nav_account_to_nav_payment_history)
                }
                2 -> {
                    findNavController().navigate(R.id.action_nav_account_to_nav_settings)
                }
                else -> {
                    (requireActivity() as MainActivity).signOut()
                }
            }
        }
        binding.actionRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = settingsAdapter
        }
        settingsAdapter.submitList(getActionList())

        accountViewModel.userProfile.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding.apply {
                weightValue.text = "${it.weight} KG"
                heightValue.text = "${it.height} CM"
                bmiValue.text =
                    (it.weight.toFloat() / sqrt(it.height.toFloat()) * 10).toString().take(4)

                Glide.with(accountImage)
                    .load(it.imageUrl)
                    .circleCrop()
                    .error(R.drawable.sample_profile)
                    .into(accountImage)
            }
        }
        accountViewModel.syncUserProfile()

        return binding.root
    }

    private fun getActionList(): List<ActionItem> {
        val actionItems = LinkedList<ActionItem>()
        actionItems.add(ActionItem(R.drawable.icon_account, "Update Account"))
        actionItems.add(ActionItem(R.drawable.icon_payment, "Payment History"))
        // actionItems.add(ActionItem(R.drawable.icon_settings, "Settings"))
        actionItems.add(ActionItem(R.drawable.icon_exits, "Sign Out"))
        return actionItems
    }
}