package com.bccowo.naiz.presentation.detector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.Extras.EXTRA_CANDI
import com.bccowo.naiz.core.config.Extras.EXTRA_IMAGE
import com.bccowo.naiz.core.config.Extras.EXTRA_RESULT
import com.bccowo.naiz.core.ui.OrnamentViewPagerAdapter
import com.bccowo.naiz.core.util.Image
import com.bccowo.naiz.databinding.ActivityDetectionDetailBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DetectionDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectionDetailBinding
    private val detectorViewModel: DetectorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val candi = intent.getParcelableExtra<Candi>(EXTRA_CANDI) as Candi
        val imagePath = intent.getStringExtra(EXTRA_IMAGE) as String
        val result =
            intent.getParcelableExtra<DetectionResult>(EXTRA_RESULT) as DetectionResult

        binding.ornamentName.text = result.name
        binding.ornamentDescription.text = result.description
        binding.ornamentAddress.text = candi.address

        detectorViewModel.submitCandiScan(candi.id)
        val imageFile = File(imagePath)
        val bitmap = Image.createOptimizedImage(imageFile.absolutePath)
        val scaledBitmap = Image.createScaledImage(bitmap)
        binding.ornamentImage.load(scaledBitmap)

        val reliefName = result.name.split(" ")[1]
        val viewPagerAdapter = OrnamentViewPagerAdapter(this, reliefName, candi.id)
        binding.viewpager.adapter = viewPagerAdapter

        val tabTitles = resources.getStringArray(R.array.ornament_tab_name)
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.btnBackToHome.setOnClickListener {
            finish()
        }
    }
}