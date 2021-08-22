package com.bccowo.naiz.presentation.detail_candi

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ActivityDetailCandiBinding
import com.bccowo.naiz.databinding.DialogAddRatingBinding
import com.bccowo.naiz.domain.model.Candi
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCandiActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CANDI_DETAIL = "EXTRA_CANDI_DETAIL"
        val TAB_TITLES = listOf("Ornamen", "Tampilkan Peta", "Candi Terdekat")
    }

    private lateinit var binding: ActivityDetailCandiBinding
    private val detailCandiViewModel: DetailCandiViewModel by viewModel()
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCandiBinding.inflate(layoutInflater)
        initView()
        initToolbar()

        val candi = intent.getParcelableExtra<Candi>(EXTRA_CANDI_DETAIL) as Candi
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
            Toast.makeText(this, "Rating Given: $ratingInt", Toast.LENGTH_SHORT).show()
            alertDialog.cancel()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}