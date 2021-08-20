package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NaizApi {

    @POST("/sign_up")
    suspend fun registerUser(@Body registerBody: RegisterRequest): BasicResponse
}