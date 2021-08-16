package com.bccowo.naiz.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bccowo.naiz.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    companion object {
        const val LANDING_DATA = "LANDING_DATA"
    }

    private lateinit var binding: FragmentOnboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<OnboardingData>(LANDING_DATA)
        data?.let {
            with(binding) {
                image.setImageResource(it.image)
                title.text = it.title
                description.text = it.description
            }
        }
    }
}