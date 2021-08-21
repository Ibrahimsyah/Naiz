package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.core.data.source.remote.response.LoginResponse
import com.bccowo.naiz.domain.repository.INaizRepository

class UserInteractor(private val naizRepository: INaizRepository) : UserUseCase {
    override suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return naizRepository.registerUser(registerRequest)
    }

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return naizRepository.loginUser(loginRequest)
    }
}