package com.bccowo.naiz.domain.model

data class QuizQuestion(
    val id: Int,
    val question: String,
    val image: String?,
    val choices: List<QuizOptions>,
)

data class QuizOptions(
    val option: String,
    val isTrue: Boolean
)