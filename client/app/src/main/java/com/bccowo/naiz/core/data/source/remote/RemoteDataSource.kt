package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.*

class RemoteDataSource(private val naizApi: NaizApi) {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return naizApi.registerUser(registerRequest)
    }

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return naizApi.loginUser(loginRequest)
    }

    suspend fun getAllCandi(accessToken: String): List<CandiResponse> {
        val auth = "Bearer $accessToken"
        return naizApi.getAllCandi(auth).data
    }

    suspend fun checkCredentials(accessToken: String) {
        val auth = "Bearer $accessToken"
        naizApi.checkCredentials(auth)
    }

    suspend fun getQuiz(accessToken: String): List<QuizResponse> {
        val auth = "Bearer $accessToken"
        return naizApi.getQuiz(auth).data
    }

    suspend fun getQuizQuestions(accessToken: String, quizId: Int): List<QuizQuestionResponse> {
        val auth = "Bearer $accessToken"
        val quizIdQuery = "eq.$quizId"
        return naizApi.getQuizQuestions(auth, quizIdQuery).data
    }
}