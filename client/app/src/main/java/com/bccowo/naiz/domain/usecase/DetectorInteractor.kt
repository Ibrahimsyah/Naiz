package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.DetectionResult
import com.bccowo.naiz.domain.repository.INaizRepository

class DetectorInteractor(private val naizRepository: INaizRepository): DetectorUseCase {
    override suspend fun detectImage(imagePath: String): DetectionResult {
        return naizRepository.predictImage(imagePath)
    }
}