package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Relief
import com.bccowo.naiz.domain.repository.INaizRepository

class ReliefInteractor(private val naizRepository: INaizRepository) : ReliefUseCase {
    override suspend fun getSimilarRelief(reliefName: String, accessToken: String): List<Relief> {
        return naizRepository.getSimilarRelief(reliefName, accessToken)
    }

    override suspend fun getOtherRelief(candiId: Int, accessToken: String): List<Relief> {
        return naizRepository.getOtherRelief(candiId, accessToken)
    }
}