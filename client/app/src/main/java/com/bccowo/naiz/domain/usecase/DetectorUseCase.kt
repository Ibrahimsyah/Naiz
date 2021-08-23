package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.DetectionResult

interface DetectorUseCase {
    suspend fun detectImage(imagePath: String): DetectionResult
}