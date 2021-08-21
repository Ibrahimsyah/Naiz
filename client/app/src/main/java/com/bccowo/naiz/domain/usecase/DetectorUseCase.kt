package com.bccowo.naiz.domain.usecase

import android.graphics.Bitmap

interface DetectorUseCase {
    suspend fun detectImage(image: Bitmap, token: String)
}