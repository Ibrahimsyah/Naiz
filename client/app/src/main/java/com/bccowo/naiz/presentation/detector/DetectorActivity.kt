package com.bccowo.naiz.presentation.detector

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.exifinterface.media.ExifInterface
import com.bccowo.naiz.databinding.ActivityDetectorBinding
import com.bccowo.naiz.databinding.DialogLoadingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DetectorActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH"
    }

    private val detectorViewModel: DetectorViewModel by viewModel()
    private lateinit var binding: ActivityDetectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectorBinding.inflate(layoutInflater)
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        val imagePath = intent.getStringExtra(EXTRA_IMAGE_PATH) as String
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
        binding.detectorImage.setImageBitmap(bitmap)

        val layout = DialogLoadingBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this).apply {
            setView(layout.root)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        detectorViewModel.detectImage(bitmap).observe(this, {
            if (it) {
                val intent = Intent(this, DetectionResultActivity::class.java).apply {
                    putExtra(
                        DetectionResultActivity.EXTRA_STATUS,
                        DetectionResultActivity.STATUS_SUCCESS
                    )
                }
                startActivity(intent)
                finish()
            }
        })
    }
}