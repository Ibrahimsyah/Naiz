package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.repository.INaizRepository

class CandiInteractor(private val naizRepository: INaizRepository) : CandiUseCase {
    override fun checkCandiBookmarked(candiId: Int): LiveData<Boolean> {
        return naizRepository.checkCandiBookmarked(candiId)
    }

    override fun getBookmarkedCandi(): LiveData<List<Candi>> {
        return liveData {  }
    }

    override suspend fun insertBookmark(candi: Candi) {
        naizRepository.addCandiToBookmark(candi)
    }

    override suspend fun removeBookmark(candi: Candi) {
       naizRepository.removeCandiFromBookmark(candi)
    }

    override suspend fun removeAllBookmarks() {

    }

    override fun getPopularCandi(): LiveData<List<Candi>> {
        return liveData {
            emit(List(5) {
                Candi(
                    it,
                    "Candi Prambanan",
                    "Malang",
                    "https://inibaru.id/media/12275/large/normal/e1e09d11-a088-4e01-8025-7bfacc941142__large.jpg",
                    8.5,
                )
            })
        }
    }

    override fun getCandiProgress(): LiveData<List<CandiProgress>> {
        return liveData {
            emit(List(5) {
                CandiProgress(
                    "Candi Prambanan",
                    "9/10",
                    "https://inibaru.id/media/12275/large/normal/e1e09d11-a088-4e01-8025-7bfacc941142__large.jpg",
                )
            })
        }
    }

    override fun getAllCandi(): LiveData<List<Candi>> {
        return liveData {
            emit(List(5) {
                Candi(
                    it,
                    "Candi Prambanan",
                    "Malang",
                    "https://inibaru.id/media/12275/large/normal/e1e09d11-a088-4e01-8025-7bfacc941142__large.jpg",
                    8.5,
                )
            })
        }
    }
}