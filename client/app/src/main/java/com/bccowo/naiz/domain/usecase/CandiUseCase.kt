package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress

interface CandiUseCase {
    fun checkCandiBookmarked(candiId: Int): LiveData<Boolean>
    suspend fun insertBookmark(candi: Candi)
    suspend fun removeBookmark(candi: Candi)

    fun getPopularCandi(): LiveData<List<Candi>>
    fun getCandiProgress(): LiveData<List<CandiProgress>>
    fun getAllCandi(): LiveData<List<Candi>>
}