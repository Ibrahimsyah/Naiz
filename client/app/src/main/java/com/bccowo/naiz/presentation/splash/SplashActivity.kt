package com.bccowo.naiz.presentation.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ActivitySplashBinding
import com.bccowo.naiz.presentation.home.HomeActivity
import com.bccowo.naiz.presentation.onboarding.OnboardingActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DELAY_MILLIS = 500L
    }

    private val splashViewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Dexter.withContext(this)
            .withPermission(Manifest.permission.ACCESS_NETWORK_STATE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    val isConnectionAvailable = checkConnectionAvailable()
                    if (!isConnectionAvailable) {
                        exitAppNicely()
                    } else {
                        checkCredentials()
                    }
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {}

            }).check()
    }

    private fun exitAppNicely() {
        Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_SHORT).show()
        Handler(mainLooper).postDelayed({
            finish()
        }, SPLASH_DELAY_MILLIS * 2)
    }

    private fun checkCredentials() {
        splashViewModel.checkCredentials().observe(this, {
            it?.let {
                val destIntent =
                    if (it) HomeActivity::class.java else OnboardingActivity::class.java

                Handler(mainLooper).postDelayed({
                    val intent = Intent(this, destIntent)
                    startActivity(intent)
                    finish()
                }, SPLASH_DELAY_MILLIS)
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun checkConnectionAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}