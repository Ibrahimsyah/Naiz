package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.QuizResultRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.*
import retrofit2.http.*

interface NaizApi {

    @POST("/sign_up")
    suspend fun registerUser(@Body registerBody: RegisterRequest): BasicResponse

    @POST("/sign_in?column=email, password, name, id, phone")
    suspend fun loginUser(@Body loginBody: LoginRequest): LoginResponse

    @GET("/u/fetch/temples?select=id, name, description, address, image, longitude, latitude, rating:temple_reviews(rate)")
    suspend fun getAllCandi(@Header("Authorization") authorization: String): CandiResponseBody

    @GET("/u/fetch/temples?select=id, name, description, address, image, longitude, latitude, rating:temple_reviews(rate)")
    suspend fun searchCandi(
        @Header("Authorization") authorization: String,
        @Query("name") searchQuery: String
    ): CandiResponseBody

    @GET("/u/fetch/users?select=id")
    suspend fun checkCredentials(@Header("Authorization") authorization: String)

    @GET("/u/fetch/quiz_packs")
    suspend fun getQuiz(@Header("Authorization") authorization: String): QuizResponseBody

    @GET("/u/fetch/quiz_questions?select=question, image, options: quiz_options(option, is_true)")
    suspend fun getQuizQuestions(
        @Header("Authorization") authorization: String,
        @Query("quiz_pack_id") quizId: String
    ): QuizQuestionResponseBody

    @POST("/u/quiz")
    suspend fun submitQuizResult(
        @Header("Authorization") authorization: String,
        @Body quizResultRequest: QuizResultRequest
    ) : BasicResponse
}