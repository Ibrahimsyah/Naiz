package com.bccowo.naiz.domain.repository

import androidx.lifecycle.LiveData
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import com.bccowo.naiz.domain.model.*

interface INaizRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
    suspend fun checkCredential(accessToken: String)
    suspend fun getAllCandi(accessToken: String): List<Candi>
    suspend fun getRelatedCandi(candiId: Int, accessToken: String): List<Candi>
    suspend fun searchCandi(accessToken: String, query: String): List<Candi>
    suspend fun getAllQuiz(accessToken: String): List<Quiz>
    suspend fun getQuizQuestions(accessToken: String, quizId: Int): List<QuizQuestion>
    suspend fun predictImage(imagePath: String): DetectionResult
    suspend fun submitQuizResult(quizId: Int, score: Int, accessToken: String): BasicResponse
    suspend fun submitCandiReview(candiId: Int, rate: Int, accessToken: String): BasicResponse
    suspend fun submitCandiScan(candiId: Int, accessToken: String): BasicResponse
    suspend fun getCandiScanCount(candiId: Int, userId: Int, accessToken: String): Int
    suspend fun getSimilarRelief(reliefName: String, accessToken: String): List<Relief>
    suspend fun getOtherRelief(candiId: Int, accessToken: String): List<Relief>
    suspend fun getCandiRelief(candiId: Int, accessToken: String): List<Relief>

    suspend fun addCandiToBookmark(candi: Candi)
    suspend fun removeCandiFromBookmark(candi: Candi)
    suspend fun removeAllBookmarkedCandis()
    fun getAllBookmarkedCandis(): LiveData<List<Candi>>
    fun checkCandiBookmarked(candiId: Int) : LiveData<Boolean>
}