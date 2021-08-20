package com.bccowo.naiz.presentation.detail_candi

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bccowo.naiz.presentation.detail_candi.map.MapFragment
import com.bccowo.naiz.presentation.detail_candi.nearest_candi.NearestCandiFragment
import com.bccowo.naiz.presentation.detail_candi.ornament.OrnamentFragment

class DetailViewPagerAdapter(app: AppCompatActivity) : FragmentStateAdapter(app) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = listOf(NearestCandiFragment(), MapFragment(), OrnamentFragment())
        return fragment[position]
    }
}