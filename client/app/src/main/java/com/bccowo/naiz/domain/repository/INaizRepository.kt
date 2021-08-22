package com.bccowo.naiz.domain.repository

import androidx.lifecycle.LiveData
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import com.bccowo.naiz.domain.model.Candi

interface INaizRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
    suspend fun checkCredential(accessToken: String)
    suspend fun getPopularCandi(accessToken: String): List<Candi>

    suspend fun addCandiToBookmark(candi: Candi)
    suspend fun removeCandiFromBookmark(candi: Candi)
    suspend fun removeAllBookmarkedCandis()
    fun getAllBookmarkedCandis(): LiveData<List<Candi>>
    fun checkCandiBookmarked(candiId: Int) : LiveData<Boolean>
}