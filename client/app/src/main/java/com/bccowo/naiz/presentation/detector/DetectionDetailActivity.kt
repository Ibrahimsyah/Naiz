package com.bccowo.naiz.presentation.detector

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.exifinterface.media.ExifInterface
import coil.load
import com.bccowo.naiz.databinding.ActivityDetectionDetailBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DetectionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectionDetailBinding
    private val detectorViewModel: DetectorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val candi = intent.getParcelableExtra<Candi>(DetectionResultActivity.EXTRA_CANDI) as Candi
        val imagePath = intent.getStringExtra(DetectionResultActivity.EXTRA_IMAGE) as String
        val result =
            intent.getParcelableExtra<DetectionResult>(DetectionResultActivity.EXTRA_RESULT)

        binding.ornamentName.text = result?.name
        binding.ornamentDescription.text = result?.description
        binding.ornamentAddress.text = candi?.address

        detectorViewModel.submitCandiScan(candi.id)
        val imageFile = File(imagePath)
        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath).run {
            val exif = ExifInterface(imageFile.absolutePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)
            val matrix = Matrix()
            when (orientation) {
                6 -> {
                    matrix.postRotate(90F)
                }
                3 -> {
                    matrix.postRotate(180F)
                }
                8 -> {
                    matrix.postRotate(270F)
                }
            }
            Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
        }
        val scaledBitmap =
            Bitmap.createScaledBitmap(bitmap, bitmap.width / 4, bitmap.height / 4, false)
        binding.ornamentImage.load(scaledBitmap)

        binding.btnBackToHome.setOnClickListener {
            finish()
        }
    }
}