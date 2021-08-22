package com.bccowo.naiz.core.data.source.remote.response

data class QuizResponseBody(
    val data: List<QuizResponse>
)

data class QuizResponse(
    val id: Int,
    val title: String,
    val description: String,
    val level: Int
)