package com.bccowo.naiz.core.data.source.remote

import android.util.Log
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class RemoteDataSource(private val naizApi: NaizApi, private val naizMLApi: NaizMLApi) {
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

    suspend fun searchCandi(accessToken: String, query: String): List<CandiResponse> {
        val auth = "Bearer $accessToken"
        val searchQuery = "ilike.%$query%"
        return naizApi.searchCandi(auth, searchQuery).data
    }

    suspend fun predictImage(photoPath: String): String {
        val requestFile = File(photoPath).asRequestBody("multipart/form-data".toMediaType())
        val body = MultipartBody.Part.createFormData("relief_image", "image", requestFile)
        return naizMLApi.predictImage(body).result
    }
}