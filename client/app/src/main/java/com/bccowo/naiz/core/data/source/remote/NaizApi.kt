package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.config.Network
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.QuizResultRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.*
import retrofit2.http.*

interface NaizApi {

    @POST(Network.ENDPOINT_SIGN_UP)
    suspend fun registerUser(@Body registerBody: RegisterRequest): BasicResponse

    @POST(Network.ENDPOINT_LOG_IN)
    suspend fun loginUser(@Body loginBody: LoginRequest): LoginResponse

    @GET(Network.ENDPOINT_ALL_CANDI)
    suspend fun getAllCandi(@Header("Authorization") authorization: String): CandiResponseBody

    @GET(Network.ENDPOINT_ALL_CANDI)
    suspend fun getRelatedCandi(
        @Header("Authorization") authorization: String,
        @Query("id") candiIdQuery: String
    ): CandiResponseBody

    @GET(Network.ENDPOINT_ALL_CANDI)
    suspend fun searchCandi(
        @Header("Authorization") authorization: String,
        @Query("name") searchQuery: String
    ): CandiResponseBody

    @GET(Network.ENDPOINT_SCAN_COUNT)
    suspend fun getCandiScanCount(
        @Header("Authorization") authorization: String,
        @Query("user_id") userIdQuery: String,
        @Query("temple_id") templeIdQuery: String
    ): CandiScanCountResponse

    @GET(Network.ENDPOINT_SIMILAR_RELIEF)
    suspend fun getSimilarRelief(
        @Header("Authorization") authorization: String,
        @Query("name") nameQuery: String
    ) : SimillarReliefResponse

    @GET(Network.ENDPOINT_OTHER_RELIEF)
    suspend fun getOtherRelief(
        @Header("Authorization") authorization: String,
        @Query("id") candiIdQuery: String
    ) : OtherReliefResponse

    @GET(Network.ENDPOINT_CHECK_CREDENTIAL)
    suspend fun checkCredentials(@Header("Authorization") authorization: String)

    @GET(Network.ENDPOINT_GET_QUIZZES)
    suspend fun getQuiz(@Header("Authorization") authorization: String): QuizResponseBody

    @GET(Network.ENDPOINT_GET_QUIZ_QUESTION)
    suspend fun getQuizQuestions(
        @Header("Authorization") authorization: String,
        @Query("quiz_pack_id") quizId: String
    ): QuizQuestionResponseBody

    @POST(Network.ENDPOINT_SUBMIT_QUIZ)
    suspend fun submitQuizResult(
        @Header("Authorization") authorization: String,
        @Body quizResultRequest: QuizResultRequest
    ) : BasicResponse

    @POST(Network.ENDPOINT_SUBMIT_REVIEW)
    suspend fun submitReview(
        @Header("Authorization") authorization: String,
        @Query("temple_id") templeId: Int,
        @Query("rate") rate: Int
    ) : BasicResponse

    @POST(Network.ENDPOINT_SUBMIT_SCAN)
    suspend fun submitScan(
        @Header("Authorization") authorization: String,
        @Query("temple_id") templeId: Int,
    ): BasicResponse
}