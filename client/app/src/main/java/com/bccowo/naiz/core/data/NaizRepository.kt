package com.bccowo.naiz.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.bccowo.naiz.core.data.source.local.LocalDataSource
import com.bccowo.naiz.core.data.source.remote.RemoteDataSource
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import com.bccowo.naiz.core.util.Mapper
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.repository.INaizRepository

class NaizRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : INaizRepository {
    override suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return remoteDataSource.registerUser(registerRequest)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return remoteDataSource.loginUser(loginRequest)
    }

    override suspend fun checkCredential(accessToken: String) {
        remoteDataSource.checkCredentials(accessToken)
    }

    override suspend fun getPopularCandi(accessToken: String): List<Candi> {
        return remoteDataSource.getPopularCandi(accessToken).map {
            Mapper.candiResponseToModel(it)
        }
    }

    override suspend fun addCandiToBookmark(candi: Candi) {
        val candiEntity = Mapper.candiModelToEntity(candi)
        localDataSource.insertBookmark(candiEntity)
    }

    override suspend fun removeCandiFromBookmark(candi: Candi) {
        val candiEntity = Mapper.candiModelToEntity(candi)
        localDataSource.deleteBookmarkedCandi(candiEntity)
    }

    override suspend fun removeAllBookmarkedCandis() = localDataSource.deleteAllBookmarks()

    override fun getAllBookmarkedCandis(): LiveData<List<Candi>> {
        return localDataSource.getAllBookmarks().map {
            Mapper.candiEntityListToModel(it)
        }
    }

    override fun checkCandiBookmarked(candiId: Int) = localDataSource.checkCandiBookmarked(candiId)

}