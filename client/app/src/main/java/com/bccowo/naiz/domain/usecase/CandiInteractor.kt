package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.model.Ornament
import com.bccowo.naiz.domain.model.OrnamentDetail
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

    override fun getCandiOrnaments(candiId: Int): LiveData<List<Ornament>> {
        return liveData {
            emit(List(6) {
                val isFound = it % 2 == 0
                Ornament(
                    1,
                    "Relief Cerita Panji",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/COLLECTIE_TROPENMUSEUM_Prins_Panji_in_een_hof_met_drie_vrouwen._TMnr_2110-1.jpg/300px-COLLECTIE_TROPENMUSEUM_Prins_Panji_in_een_hof_met_drie_vrouwen._TMnr_2110-1.jpg",
                    isFound
                )
            })
        }
    }

    override fun getOrnamentDetail(ornamentId: Int): LiveData<OrnamentDetail> {
        return liveData {
            emit(
                OrnamentDetail(
                    1,
                    "Ornament 1",
                    "12/11/2021",
                    "https://d220hvstrn183r.cloudfront.net/attachment/04987806999941934963.large",
                    "Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet Lorem Ipsum Dolor Sit Amet "
                )
            )
        }
    }

    override fun getSimilarOrnament(ornamentId: Int): LiveData<List<Ornament>> {
        return liveData {
            emit(
                List(6) {
                    val isFound = it % 2 == 0
                    Ornament(
                        1,
                        "Relief Cerita Panji",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/COLLECTIE_TROPENMUSEUM_Prins_Panji_in_een_hof_met_drie_vrouwen._TMnr_2110-1.jpg/300px-COLLECTIE_TROPENMUSEUM_Prins_Panji_in_een_hof_met_drie_vrouwen._TMnr_2110-1.jpg",
                        isFound
                    )
                })
        }
    }
}