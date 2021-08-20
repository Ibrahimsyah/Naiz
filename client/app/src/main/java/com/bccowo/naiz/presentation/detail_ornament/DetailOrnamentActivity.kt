package com.bccowo.naiz.presentation.detail_ornament

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.ActivityDetailOrnamentBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailOrnamentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ORNAMENT_ID = "ORNAMENT_ID"
        private val TAB_TITLES = arrayOf("Kemiripan", "Ornamen Lainnya")
    }

    private lateinit var binding: ActivityDetailOrnamentBinding
    private val detailOrnamentViewModel: DetailOrnamentViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrnamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding = ActivityDetailOrnamentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val ornamentId = intent.getIntExtra(EXTRA_ORNAMENT_ID, -1)
        detailOrnamentViewModel.getDetailOrnament(ornamentId).observe(this, {
            with(binding) {
                ornamentImage.load(it.image)
                ornamentName.text = it.name
                ornamentFoundDate.text =
                    String.format(getString(R.string.ornament_found_at), it.foundAt)
                ornamentDescription.text = it.description
            }
        })

        val detailOrnamentViewPagerAdapter = DetailOrnamentViewPagerAdapter(this)
        binding.viewpager.adapter = detailOrnamentViewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, index ->
            tab.text = TAB_TITLES[index]
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}