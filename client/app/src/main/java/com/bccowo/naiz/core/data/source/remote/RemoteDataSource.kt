package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse

class RemoteDataSource(private val naizApi: NaizApi) {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return naizApi.registerUser(registerRequest)
    }

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return naizApi.loginUser(loginRequest)
    }
}