package com.jatinvashisht.learningbiometricinandroid.biometric

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.jatinvashisht.learningbiometricinandroid.R

object BiometricPromptUtils {
    private const val TAG = "BiometricPromtUtils"
    fun createBiometricPromt(
        activity: ComponentActivity,
        processSuccess: (BiometricPrompt.AuthenticationResult) -> Unit
    ): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)
        val callback = object: BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Log.d(TAG, "errCode is $errorCode and errString is $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d(TAG, "User biometric rejected")
            }
        }
        return BiometricPrompt(activity as FragmentActivity, executor, callback)
    }
    fun createPromptInfo(activity: ComponentActivity): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder().apply {
            setTitle(activity.getString(R.string.prompt_info_title))
            setSubtitle(activity.getString(R.string.prompt_info_subtitle))
            setDescription(activity.getString(R.string.prompt_info_description))
            setConfirmationRequired(false)
            setNegativeButtonText(activity.getString(R.string.prompt_info_use_app_password))
        }
            .build()
}