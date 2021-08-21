package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.CandiResponse
import com.bccowo.naiz.core.data.source.remote.response.CandiResponseBody
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NaizApi {

    @POST("/sign_up")
    suspend fun registerUser(@Body registerBody: RegisterRequest): BasicResponse

    @POST("/sign_in?column=email,password,name,id")
    suspend fun loginUser(@Body loginBody: LoginRequest): LoginResponse

    @GET("/u/fetch/temples?select=id, name, address, image, rating: temple_reviews(rate)")
    suspend fun getPopularCandi(@Header("Authorization") authorization: String): CandiResponseBody
}