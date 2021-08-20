package com.bccowo.naiz.presentation.detail_ornament

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bccowo.naiz.presentation.detail_ornament.similar_ornament.SimilarOrnamentFragment

class DetailOrnamentViewPagerAdapter(app: AppCompatActivity) : FragmentStateAdapter(app) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = listOf(SimilarOrnamentFragment(), SimilarOrnamentFragment())
        return fragment[position]
    }
}