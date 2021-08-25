package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Relief(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val type: String,
) : Parcelable