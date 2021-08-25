package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Candi(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val image: String,
    val rating: Double,
    val ratingCount: Int,
    val longitude: Double,
    val latitude: Double,
    val reliefs: List<Relief>,
    val totalReliefs: Int,
    var scannedRelief: Int = 0
) : Parcelable