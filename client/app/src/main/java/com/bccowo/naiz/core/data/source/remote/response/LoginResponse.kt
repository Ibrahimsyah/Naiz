package com.bccowo.naiz.core.data.source.remote.response

data class LoginResponse(
    val success: Boolean,
    val data: LoginData?
)

data class LoginData(
    val token: String
)