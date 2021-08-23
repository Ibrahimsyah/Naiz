package com.bccowo.naiz.presentation.detail_candi

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.presentation.detail_candi.map.MapFragment
import com.bccowo.naiz.presentation.detail_candi.nearest_candi.NearestCandiFragment
import com.bccowo.naiz.presentation.detail_candi.ornament.OrnamentFragment

class DetailViewPagerAdapter(app: AppCompatActivity, private val candi: Candi) :
    FragmentStateAdapter(app) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val mapFragment = MapFragment.createInstance(candi.longitude, candi.latitude)
        val fragment = listOf(OrnamentFragment(), mapFragment, NearestCandiFragment())
        return fragment[position]
    }
}