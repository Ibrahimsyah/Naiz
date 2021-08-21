package com.bccowo.naiz.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.databinding.ActivitySplashBinding
import com.bccowo.naiz.presentation.home.HomeActivity
import com.bccowo.naiz.presentation.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    companion object{
        private const val SPLASH_DELAY_MILLIS = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(SharedPreference.GLOBAL_PREF, Context.MODE_PRIVATE)
        val isLoggedIn = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String
        val destIntent =
            if (isLoggedIn.isNotEmpty()) HomeActivity::class.java else OnboardingActivity::class.java

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, destIntent)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY_MILLIS)
    }
}