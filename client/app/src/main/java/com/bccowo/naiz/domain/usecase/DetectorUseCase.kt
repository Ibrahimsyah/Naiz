package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.DetectionResult

interface DetectorUseCase {
    suspend fun detectImage(imagePath: String): DetectionResult
    suspend fun submitCandiScan(candiId: Int, accessToken: String): BasicResponse
}