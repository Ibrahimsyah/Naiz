package com.bccowo.naiz.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.databinding.ActivitySplashBinding
import com.bccowo.naiz.presentation.home.HomeActivity
import com.bccowo.naiz.presentation.onboarding.OnboardingActivity
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
}