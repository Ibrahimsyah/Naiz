package com.bccowo.naiz.presentation.detector

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bccowo.naiz.core.config.Storage.AUTHORITY
import com.bccowo.naiz.core.util.Extension.gone
import com.bccowo.naiz.core.util.Extension.visible
import com.bccowo.naiz.databinding.ActivityDetectionResultBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import com.bccowo.naiz.presentation.home.HomeActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class DetectionResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STATUS = "EXTRA_STATUS"
        const val EXTRA_CANDI = "EXTRA_CANDI"
        const val EXTRA_IMAGE = "EXTRA_IMAGE"
        const val EXTRA_RESULT = "EXTRA_RESULT"
        const val STATUS_SUCCESS = 1
        const val STATUS_FAILED = 2
        private const val SUCCESS_DELAY_MILS = 2000L
    }

    private var imagePath = ""
    private lateinit var binding: ActivityDetectionResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val status = intent.getIntExtra(EXTRA_STATUS, STATUS_FAILED)
        initViewByStatus(status)

        if (status == STATUS_SUCCESS) {
            val candi = intent.getParcelableExtra<Candi>(EXTRA_CANDI)
            val image = intent.getStringExtra(EXTRA_IMAGE)
            val result = intent.getParcelableExtra<DetectionResult>(EXTRA_RESULT)
            Handler(mainLooper).postDelayed({
                val intent = Intent(this, DetectionDetailActivity::class.java)
                intent.putExtra(EXTRA_CANDI, candi)
                intent.putExtra(EXTRA_IMAGE, image)
                intent.putExtra(EXTRA_RESULT, result)
                startActivity(intent)
                finish()
            }, SUCCESS_DELAY_MILS)
        }

        with(binding) {
            btnBackToHome.setOnClickListener { finish() }
            btnRescan.setOnClickListener { launchCamera() }
        }
    }

    private fun initViewByStatus(status: Int) {
        val success = status == STATUS_SUCCESS
        with(binding) {
            successLogo.apply { if (success) visible() else gone() }
            successDescription.apply { if (success) visible() else gone() }
            scanResultTitle.apply { if (!success) visible() else gone() }
            failedDescription.apply { if (!success) visible() else gone() }
            failedLogo.apply { if (!success) visible() else gone() }
            btnBackToHome.apply { if (!success) visible() else gone() }
            btnRescan.apply { if (!success) visible() else gone() }
        }
    }

    private fun launchCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        AUTHORITY,
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, HomeActivity.REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            imagePath = absolutePath
        }
    }

    private fun sendImagePathToDetector(path: String) {
        val intent = Intent(this, DetectorActivity::class.java).apply {
            putExtra(DetectorActivity.EXTRA_IMAGE_PATH, path)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == HomeActivity.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            sendImagePathToDetector(imagePath)
            finish()
        }
    }
}