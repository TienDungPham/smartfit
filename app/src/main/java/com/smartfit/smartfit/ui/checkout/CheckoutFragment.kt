package com.smartfit.smartfit.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.FragmentCheckoutBinding
import javax.inject.Inject

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val checkoutViewModel by viewModels<CheckoutViewModel> {
        viewModelFactory
    }

    private val wvc = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            url?.apply {
                if (startsWith("https://smartfitapi2.herokuapp.com/payment/checkout-")) {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        binding.toolbar.setupWithNavController(findNavController())
        appComponent(requireContext()).injectCheckoutFragment(this)

        binding.toolbar.setupWithNavController(findNavController())
        checkoutViewModel.userAccess.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.checkoutView.apply {
                    webViewClient = wvc
                    webChromeClient = WebChromeClient()
                    settings.javaScriptEnabled = true
                    loadUrl("https://smartfitapi2.herokuapp.com/payment/checkout?accessToken=${it.accessToken}")
                }
            }
        }
        checkoutViewModel.findUserAccess()

        return binding.root
    }
}