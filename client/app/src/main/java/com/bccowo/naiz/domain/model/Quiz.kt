package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    val id: Int,
    val level: Int,
    val description: String,
    val title: String,
    val status: String
) : Parcelable