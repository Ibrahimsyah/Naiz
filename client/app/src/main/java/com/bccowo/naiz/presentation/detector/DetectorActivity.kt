package com.bccowo.naiz.presentation.detector

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.core.config.Extras.EXTRA_CANDI
import com.bccowo.naiz.core.config.Extras.EXTRA_IMAGE
import com.bccowo.naiz.core.config.Extras.EXTRA_RESULT
import com.bccowo.naiz.core.config.Extras.EXTRA_STATUS
import com.bccowo.naiz.core.util.Image
import com.bccowo.naiz.databinding.ActivityDetectorBinding
import com.bccowo.naiz.databinding.DialogLoadingBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DetectorActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IMAGE_PATH = "EXTRA_IMAGE_PATH"
    }

    private val detectorViewModel: DetectorViewModel by viewModel()
    private lateinit var binding: ActivityDetectorBinding
    private lateinit var selectedCandi: Candi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectorBinding.inflate(layoutInflater)
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        val imagePath = intent.getStringExtra(EXTRA_IMAGE_PATH) as String
        val imageFile = File(imagePath)
        val bitmap = Image.createOptimizedImage(imageFile.absolutePath)

        binding.detectorImage.setImageBitmap(bitmap)

        val bottomSheetDialog = BottomSheetDialog.getInstance(supportFragmentManager) {}

        val layout = DialogLoadingBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this).apply {
            setView(layout.root)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        detectorViewModel.getCandiData().observe(this, {
            bottomSheetDialog.showData(it) { candi ->
                detectorViewModel.detectImage(this, bitmap)
                selectedCandi = candi
                true
            }
        })

        detectorViewModel.loading.observe(this, {
            if (it) dialog.show() else dialog.cancel()
        })

        detectorViewModel.detectionResult.observe(this, {
            onDetectionResult(it, imagePath)
        })
    }

    private fun onDetectionResult(it: DetectionResult?, imagePath: String) {
        it?.let {
            val intent = Intent(this, DetectionResultActivity::class.java).apply {
                putExtra(EXTRA_STATUS, DetectionResultActivity.STATUS_SUCCESS)
                putExtra(EXTRA_CANDI, selectedCandi)
                putExtra(EXTRA_IMAGE, imagePath)
                putExtra(EXTRA_RESULT, it)
            }
            startActivity(intent)
            finish()
        }
    }

}