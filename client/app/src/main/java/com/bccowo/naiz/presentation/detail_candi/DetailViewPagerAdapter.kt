package com.bccowo.naiz.presentation.detail_candi

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailViewPagerAdapter(app: AppCompatActivity) : FragmentStateAdapter(app) {

    override fun getItemCount(): Int = 0

    override fun createFragment(position: Int): Fragment {
        val fragment = listOf<Fragment>()
        return fragment[position]
    }
}