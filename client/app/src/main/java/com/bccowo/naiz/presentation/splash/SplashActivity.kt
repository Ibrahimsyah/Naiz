package com.bccowo.naiz.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bccowo.naiz.databinding.ActivitySplashBinding
import com.bccowo.naiz.presentation.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    companion object{
        private const val SPLASH_DELAY_MILLIS = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY_MILLIS)
    }
}