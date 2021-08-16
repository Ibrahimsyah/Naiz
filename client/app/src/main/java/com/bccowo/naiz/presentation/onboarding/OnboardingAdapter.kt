package com.bccowo.naiz.presentation.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bccowo.naiz.presentation.onboarding.OnboardingFragment.Companion.LANDING_DATA

class OnboardingAdapter(activity: AppCompatActivity, private val data: List<OnboardingData>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment().apply {
            arguments = Bundle().apply {
                putParcelable(LANDING_DATA, data[position])
            }
        }
    }
}