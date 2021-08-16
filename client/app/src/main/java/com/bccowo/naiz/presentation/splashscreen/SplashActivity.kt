package com.bccowo.naiz.presentation.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bccowo.naiz.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    companion object{
        private const val SPLASH_DELAY_MILLIS = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}