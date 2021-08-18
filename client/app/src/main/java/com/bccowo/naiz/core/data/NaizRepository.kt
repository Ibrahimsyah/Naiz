package com.bccowo.naiz.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.repository.INaizRepository

class NaizRepository : INaizRepository {
    override suspend fun addCandiToBookmark(candi: Candi) {

    }

    override suspend fun removeCandiFromBookmark(candi: Candi) {

    }

    override fun checkCandiBookmarked(candiId: Int): LiveData<Boolean> {
        return MutableLiveData(false)
    }

}