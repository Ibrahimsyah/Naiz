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
import com.bccowo.naiz.domain.model.*
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

    override suspend fun getAllCandi(accessToken: String): List<Candi> {
        return remoteDataSource.getAllCandi(accessToken).map {
            Mapper.candiResponseToModel(it)
        }
    }

    override suspend fun getRelatedCandi(candiId: Int, accessToken: String): List<Candi> {
        return remoteDataSource.getRelatedCandi(candiId, accessToken).map {
            Mapper.candiResponseToModel(it)
        }
    }

    override suspend fun searchCandi(accessToken: String, query: String): List<Candi> {
        return remoteDataSource.searchCandi(accessToken, query)
            .map { Mapper.candiResponseToModel(it) }
    }

    override suspend fun getAllQuiz(accessToken: String): List<Quiz> {
        return remoteDataSource.getQuiz(accessToken).map { Mapper.quizResponseToModel(it) }
    }

    override suspend fun getQuizQuestions(accessToken: String, quizId: Int): List<QuizQuestion> {
        return remoteDataSource.getQuizQuestions(accessToken, quizId).map {
            Mapper.quizQuestionsResponseToModel(it)
        }
    }

    override suspend fun predictImage(imagePath: String): DetectionResult {
        val result = remoteDataSource.predictImage(imagePath)
        return DetectionResult(result.name, result.description)
    }

    override suspend fun submitQuizResult(
        quizId: Int,
        score: Int,
        accessToken: String
    ): BasicResponse {
        return remoteDataSource.submitQuizResult(quizId, score, accessToken)
    }

    override suspend fun submitCandiReview(
        candiId: Int,
        rate: Int,
        accessToken: String
    ): BasicResponse {
        return remoteDataSource.submitCandiReview(candiId, rate, accessToken)
    }

    override suspend fun submitCandiScan(candiId: Int, accessToken: String): BasicResponse {
        return remoteDataSource.submitCandiScan(candiId, accessToken)
    }

    override suspend fun getCandiScanCount(candiId: Int, userId: Int, accessToken: String): Int {
        return remoteDataSource.getCandiScanCount(candiId, userId, accessToken)
    }

    override suspend fun getSimilarRelief(reliefName: String, accessToken: String): List<Relief> {
        return remoteDataSource.getSimilarRelief(reliefName, accessToken).map {
            Mapper.similarReliefResponseToModel(it)
        }
    }

    override suspend fun getOtherRelief(candiId: Int, accessToken: String): List<Relief> {
        return remoteDataSource.getOtherRelief(candiId, accessToken).map {
            Mapper.similarReliefResponseToModel(it.relief)
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