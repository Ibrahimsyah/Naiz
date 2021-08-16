package com.bccowo.naiz.presentation.onboarding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingData(
    val title: String,
    val description: String,
    val image: Int
) : Parcelable