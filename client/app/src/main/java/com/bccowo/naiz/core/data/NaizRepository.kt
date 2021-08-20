package com.bccowo.naiz.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bccowo.naiz.core.data.source.remote.RemoteDataSource
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.repository.INaizRepository

class NaizRepository(private val remoteDataSource: RemoteDataSource) : INaizRepository {
    override suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return remoteDataSource.registerUser(registerRequest)
    }

    override suspend fun addCandiToBookmark(candi: Candi) {

    }

    override suspend fun removeCandiFromBookmark(candi: Candi) {

    }

    override fun checkCandiBookmarked(candiId: Int): LiveData<Boolean> {
        return MutableLiveData(false)
    }

}