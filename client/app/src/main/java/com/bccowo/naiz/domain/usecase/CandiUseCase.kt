package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.model.Ornament

interface CandiUseCase {
    fun checkCandiBookmarked(candiId: Int): LiveData<Boolean>
    fun getBookmarkedCandi(): LiveData<List<Candi>>
    suspend fun insertBookmark(candi: Candi)
    suspend fun removeBookmark(candi: Candi)
    suspend fun removeAllBookmarks()

    fun getPopularCandi(): LiveData<List<Candi>>
    fun getCandiProgress(): LiveData<List<CandiProgress>>
    fun getAllCandi(): LiveData<List<Candi>>
    fun getCandiOrnaments(candiId: Int): LiveData<List<Ornament>>
}