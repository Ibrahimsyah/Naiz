package com.bccowo.naiz.core.data.source.remote.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)