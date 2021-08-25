package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Relief

interface ReliefUseCase {
    suspend fun getSimilarRelief(reliefName: String, accessToken: String): List<Relief>
    suspend fun getOtherRelief(candiId: Int, accessToken: String): List<Relief>
    suspend fun getCandiRelief(candiId: Int, accessToken: String): List<Relief>
}