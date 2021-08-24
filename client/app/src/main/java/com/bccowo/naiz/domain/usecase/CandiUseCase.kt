package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.model.Ornament
import com.bccowo.naiz.domain.model.OrnamentDetail

interface CandiUseCase {
    fun checkCandiBookmarked(candiId: Int): LiveData<Boolean>
    fun getBookmarkedCandi(): LiveData<List<Candi>>
    suspend fun insertBookmark(candi: Candi)
    suspend fun removeBookmark(candi: Candi)
    suspend fun removeAllBookmarks()

    suspend fun getAllCandi(accessToken: String): List<Candi>
    suspend fun searchCandi(accessToken: String, query: String): List<Candi>
    fun getCandiProgress(): LiveData<List<CandiProgress>>
    suspend fun submitCandiReview(candiId: Int, rate: Int, accessToken: String): BasicResponse

    fun getCandiOrnaments(candiId: Int): LiveData<List<Ornament>>
    fun getOrnamentDetail(ornamentId: Int): LiveData<OrnamentDetail>
    fun getSimilarOrnament(ornamentId: Int): LiveData<List<Ornament>>
}