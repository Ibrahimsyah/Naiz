package com.bccowo.naiz.presentation.detail_ornament

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.bccowo.naiz.core.ui.OrnamentViewPagerAdapter
import com.bccowo.naiz.databinding.ActivityDetailOrnamentBinding
import com.bccowo.naiz.domain.model.Relief
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailOrnamentActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ORNAMENT = "ORNAMENT"
        const val EXTRA_CANDI_ID = "EXTRA_CANDI_ID"
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

        val ornament = intent.getParcelableExtra<Relief>(EXTRA_ORNAMENT) as Relief
        val candiId = intent.getIntExtra(EXTRA_CANDI_ID, 0)
        with(binding) {
            ornamentImage.load(ornament.image)
            ornamentName.text = ornament.name
            ornamentDescription.text = ornament.description
        }

        val detailOrnamentViewPagerAdapter = OrnamentViewPagerAdapter(this, ornament.type, candiId)
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