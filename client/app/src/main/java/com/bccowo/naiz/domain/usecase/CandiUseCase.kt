package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.Candi

interface CandiUseCase {
    fun checkCandiBookmarked(candiId: Int): LiveData<Boolean>
    fun getBookmarkedCandi(): LiveData<List<Candi>>
    suspend fun insertBookmark(candi: Candi)
    suspend fun removeBookmark(candi: Candi)
    suspend fun removeAllBookmarks()

    suspend fun getAllCandi(accessToken: String): List<Candi>
    suspend fun getRelatedCandi(candiId: Int, accessToken: String): List<Candi>
    suspend fun searchCandi(accessToken: String, query: String): List<Candi>
    fun getCandiProgress(userId: Int, accessToken: String): LiveData<List<Candi>>
    suspend fun submitCandiReview(candiId: Int, rate: Int, accessToken: String): BasicResponse
}