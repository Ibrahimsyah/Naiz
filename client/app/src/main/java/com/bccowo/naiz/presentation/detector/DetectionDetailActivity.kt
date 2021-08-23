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
import java.io.File

class DetectionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val candi = intent.getParcelableExtra<Candi>(DetectionResultActivity.EXTRA_CANDI)
        val imagePath = intent.getStringExtra(DetectionResultActivity.EXTRA_IMAGE) as String

        binding.ornamentName.text = "Relief Ari Darma"
        binding.ornamentDescription.text =
            "Menggambarkan terbebas-nya  naga  betina  dari  rudapaksa  naga  jantan dan pergi atau pulang menemui ayahnya yang  merupakan  raja  para  naga.  Naga  betina menceritakan  pada  ayahnya  budi  baik Ari Darma  yang  telah membebaskan dirinya dari perbuatan dosa dan aib yang memalukan. Gambaran relief untuk cerita ini adalah masih nampak suasana hutan. Selanjutnya nampak naga betina sedang bergerak dan menemui  seekor  naga  dalam  posisi  duduk dengan  ekor  menjulur  ke  atas.  Sosok  kedua naga ini menggambarkan sebagai bangsawan nampak pada mahkotanya, dan mahkota ayahnya  nampak  lebih  menandakan  sebagai raja"
        binding.ornamentAddress.text = candi?.address

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