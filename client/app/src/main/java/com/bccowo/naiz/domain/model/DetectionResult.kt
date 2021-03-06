package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResult(
    val name: String,
    val description: String
) : Parcelable
