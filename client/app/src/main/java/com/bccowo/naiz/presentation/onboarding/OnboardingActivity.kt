package com.bccowo.naiz.presentation.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ActivityOnboardingBinding
import com.bccowo.naiz.presentation.login.LoginActivity
import com.bccowo.naiz.presentation.register.RegisterActivity

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val landingData = listOf(
            OnboardingData(
                "Jelajahi Lebih Jauh",
                "Temukan dan Jelajahi Peninggalan Budaya Berupa Candi di Indonesia Serta Dapatkan Informasi Menarik Seputar Candi & Relief",
                R.drawable.landing1
            ),
            OnboardingData(
                "Informasi Relief",
                "Ambil Gambar dari Relief pada Candi dan Temukan Informasi Menarik dari Relief Tersebut. Anda Juga Dapat Menemukan Kemiripan Relief antar Candi",
                R.drawable.landing2
            ),
            OnboardingData(
                "Quiz Menarik",
                "Selesaikan Quiz untuk Mengasah Pengetahuan Anda dan Dapatkan Poin Saat Berhasil Menjawab Pertanyaan pada Quiz",
                R.drawable.landing3
            ),
        )

        val viewPagerAdapter = OnboardingAdapter(this, landingData)
        val dotAdapter = DotAdapter(landingData.size)
        binding.viewpager.adapter = viewPagerAdapter
        with(binding.rvDot) {
            layoutManager = LinearLayoutManager(
                this@OnboardingActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = dotAdapter
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotAdapter.currentPosition = position
            }
        })

        binding.getStarted.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}