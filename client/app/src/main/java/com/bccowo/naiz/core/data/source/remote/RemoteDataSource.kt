package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.core.data.source.remote.response.BasicResponse

class RemoteDataSource(private val naizApi: NaizApi) {
    suspend fun registerUser(registerRequest: RegisterRequest): BasicResponse {
        return naizApi.registerUser(registerRequest)
    }
}