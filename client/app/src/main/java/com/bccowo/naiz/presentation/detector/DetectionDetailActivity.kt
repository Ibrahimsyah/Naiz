package com.bccowo.naiz.presentation.detector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bccowo.naiz.databinding.ActivityDetectionDetailBinding

class DetectionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}