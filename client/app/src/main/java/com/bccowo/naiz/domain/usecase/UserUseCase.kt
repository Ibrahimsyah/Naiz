package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse

interface UserUseCase {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
}