package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NaizApi {

    @POST("/sign_up")
    suspend fun registerUser(@Body registerBody: RegisterRequest): BasicResponse

    @POST("/sign_in?column=email,password,name,id")
    suspend fun loginUser(@Body loginBody: LoginRequest): LoginResponse
}