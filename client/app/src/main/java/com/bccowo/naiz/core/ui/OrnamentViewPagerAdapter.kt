package com.bccowo.naiz.core.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bccowo.naiz.core.ui.other_ornament.OtherOrnamentFragment
import com.bccowo.naiz.core.ui.similar_ornament.SimilarOrnamentFragment

class OrnamentViewPagerAdapter(app: AppCompatActivity, reliefName: String, candiId: Int) :
    FragmentStateAdapter(app) {
    private val similarOrnamentFragment = SimilarOrnamentFragment.createInstance(reliefName)
    private val otherOrnamentFragment = OtherOrnamentFragment.createInstance(candiId)

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = listOf(similarOrnamentFragment, otherOrnamentFragment)
        return fragment[position]
    }
}