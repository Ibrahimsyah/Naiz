package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.*

interface CandiUseCase {
    fun checkCandiBookmarked(candiId: Int): LiveData<Boolean>
    fun getBookmarkedCandi(): LiveData<List<Candi>>
    suspend fun insertBookmark(candi: Candi)
    suspend fun removeBookmark(candi: Candi)
    suspend fun removeAllBookmarks()

    suspend fun getAllCandi(accessToken: String): List<Candi>
    fun getCandiProgress(): LiveData<List<CandiProgress>>

    fun getCandiOrnaments(candiId: Int): LiveData<List<Ornament>>
    fun getOrnamentDetail(ornamentId: Int): LiveData<OrnamentDetail>
    fun getSimilarOrnament(ornamentId: Int): LiveData<List<Ornament>>
}