package com.bccowo.naiz.presentation.detail_candi

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.core.config.Storage
import com.bccowo.naiz.databinding.ActivityDetailCandiBinding
import com.bccowo.naiz.databinding.DialogAddRatingBinding
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.presentation.detector.DetectorActivity
import com.bccowo.naiz.presentation.home.HomeActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DetailCandiActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CANDI_DETAIL = "EXTRA_CANDI_DETAIL"
        val TAB_TITLES = listOf("Ornamen", "Tampilkan Peta", "Candi Terdekat")
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    private var imagePath = ""
    private lateinit var binding: ActivityDetailCandiBinding
    private val detailCandiViewModel: DetailCandiViewModel by viewModel()
    private lateinit var candi: Candi
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCandiBinding.inflate(layoutInflater)
        initView()
        initToolbar()

        candi = intent.getParcelableExtra<Candi>(EXTRA_CANDI_DETAIL) as Candi
        with(binding) {
            candiImage.load(candi.image)
            candiAddress.text = candi.address
            candiAssets.text = "0 Relief"
            candiName.text = candi.name
            candiRatingText.text =
                String.format(getString(R.string.rating_template), candi.rating, candi.ratingCount)
            candiDescription.text = candi.description
        }

        val adapter = DetailViewPagerAdapter(this, candi)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, index ->
            tab.text = TAB_TITLES[index]
        }.attach()

        detailCandiViewModel.checkCandiBookmarked(candi.id).observe(this, {
            isFavorite = it
            val icon = if (it) R.drawable.ic_bookmark_active else R.drawable.ic_bookmark
            binding.fabBookmark.setImageResource(icon)
        })
        binding.fabBookmark.setOnClickListener {
            val message = if (isFavorite) {
                detailCandiViewModel.removeCandiFromBookmark(candi)
                "Candi berhasil dihapus dari bookmark"
            } else {
                detailCandiViewModel.addCandiToBookmark(candi)
                "Candi berhasil ditambahkan ke dalam bookmark"
            }
            Toast.makeText(
                this, message, Toast.LENGTH_SHORT
            ).show()
        }

        binding.candiRatingText.setOnClickListener {
            actionAddRating()
        }

        binding.btnScan.setOnClickListener {
            Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(PermissionCallback())
                .check()
        }
    }

    @Suppress("DEPRECATION")
    private fun initView() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initToolbar() {
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT)
        binding.collapsingToolbar.title = ""
    }

    private fun actionAddRating() {
        val view = DialogAddRatingBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(view.root)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        var ratingInt = 0
        view.ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                ratingInt = rating.toInt()
                view.ratingText.text = when (ratingInt) {
                    1 -> getString(R.string.rate_level_1)
                    2 -> getString(R.string.rate_level_2)
                    3 -> getString(R.string.rate_level_3)
                    4 -> getString(R.string.rate_level_4)
                    5 -> getString(R.string.rate_level_5)
                    else -> getString(R.string.rate_level_1)
                }
            }
        view.ratingBar.progress = 5
        view.btnCancel.setOnClickListener {
            alertDialog.cancel()
        }
        view.btnConfirmRating.setOnClickListener {
            detailCandiViewModel.submitCandiReview(candi.id, ratingInt).observe(this, {
                Toast.makeText(this, "Rating berhasil diberikan!", Toast.LENGTH_SHORT).show()
            })
            alertDialog.cancel()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
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
                        Storage.AUTHORITY,
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
        }
    }

    inner class PermissionCallback : PermissionListener {
        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
            launchCamera()
        }

        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
            Toast.makeText(
                this@DetailCandiActivity,
                getString(R.string.camera_access_prompt),
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onPermissionRationaleShouldBeShown(
            p0: PermissionRequest?,
            p1: PermissionToken?
        ) {
        }
    }
}