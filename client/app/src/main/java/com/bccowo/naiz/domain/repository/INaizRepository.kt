package com.bccowo.naiz.domain.repository

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.Candi

interface INaizRepository {
    suspend fun addCandiToBookmark(candi: Candi)
    suspend fun removeCandiFromBookmark(candi: Candi)
    fun checkCandiBookmarked(candiId: Int) : LiveData<Boolean>
}