package com.smartfit.smartfit.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.smartfit.smartfit.appComponent
import com.smartfit.smartfit.databinding.ActivityAuthBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val authViewModel by viewModels<AuthViewModel> {
        viewModelFactory
    }

    private lateinit var loadingDialog: ProgressDialog
    private val auth = FirebaseAuth.getInstance()
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            authFail()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            authViewModel.verificationId = verificationId
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent(this).injectAuthActivity(this)
        loadingDialog = ProgressDialog(this)
    }

    fun verifyPhoneNumber() {
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(authViewModel.phoneNumber!!, 60, TimeUnit.SECONDS, this, callbacks)
    }

    fun verifyCode(verifyCode: String) {
        val credential = PhoneAuthProvider.getCredential(authViewModel.verificationId!!, verifyCode)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    authViewModel.phoneIdentity = task.result?.user?.uid
                    authViewModel.verifySuccess()
                } else {
                    authViewModel.verifyFail()
                }
            }.addOnFailureListener {
                authViewModel.verifyFail()
            }
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

    fun authSuccess() {
        if (loadingDialog.isShowing) {
            loadingDialog.cancel()
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun authFail() {
        if (loadingDialog.isShowing) {
            loadingDialog.cancel()
        }
        startActivity(Intent(this, LauncherActivity::class.java))
        finish()
    }
}