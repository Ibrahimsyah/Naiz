package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.repository.INaizRepository

class CandiInteractor(private val naizRepository: INaizRepository) : CandiUseCase {
    override fun checkCandiBookmarked(candiId: Int): LiveData<Boolean> {
        return naizRepository.checkCandiBookmarked(candiId)
    }

    override fun getBookmarkedCandi(): LiveData<List<Candi>> {
        return naizRepository.getAllBookmarkedCandis()
    }

    override suspend fun insertBookmark(candi: Candi) {
        naizRepository.addCandiToBookmark(candi)
    }

    override suspend fun removeBookmark(candi: Candi) {
        naizRepository.removeCandiFromBookmark(candi)
    }

    override suspend fun removeAllBookmarks() {
        naizRepository.removeAllBookmarkedCandis()
    }

    override suspend fun getAllCandi(accessToken: String): List<Candi> {
        return naizRepository.getAllCandi(accessToken)
    }

    override suspend fun getRelatedCandi(candiId: Int, accessToken: String): List<Candi> {
        return naizRepository.getRelatedCandi(candiId, accessToken)
    }

    override suspend fun searchCandi(accessToken: String, query: String): List<Candi> {
        return naizRepository.searchCandi(accessToken, query)
    }


    override fun getCandiProgress(userId: Int, accessToken: String): LiveData<List<Candi>> {
        return liveData {
            val candi = naizRepository.getAllCandi(accessToken)
            candi.map {
                val progress = naizRepository.getCandiScanCount(it.id, userId, accessToken)
                it.scannedRelief = progress
            }
            emit(candi)
        }
    }

    override suspend fun submitCandiReview(
        candiId: Int,
        rate: Int,
        accessToken: String
    ): BasicResponse {
        return naizRepository.submitCandiReview(candiId, rate, accessToken)
    }
}