package com.bccowo.naiz.domain.usecase

import android.graphics.Bitmap
import com.bccowo.naiz.domain.repository.INaizRepository

class DetectorInteractor(private val naizRepository: INaizRepository): DetectorUseCase {
    override suspend fun detectImage(image: Bitmap, token: String) {

    }
}