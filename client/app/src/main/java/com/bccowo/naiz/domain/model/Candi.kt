package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Candi(
    val id: Int,
    val name: String,
    val address: String,
    val image: String,
    val rating: Double
) : Parcelable