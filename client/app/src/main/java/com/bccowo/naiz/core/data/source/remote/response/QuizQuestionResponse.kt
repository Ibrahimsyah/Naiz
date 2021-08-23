package com.bccowo.naiz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class QuizQuestionResponseBody(
    val data: List<QuizQuestionResponse>
)

data class QuizQuestionResponse(
    val id: Int,
    val image: String?,
    val question: String,
    val options: List<QuestionOptionsResponse>
)

data class QuestionOptionsResponse(
    val option: String,
    @SerializedName("is_true")
    val isTrue: Boolean
)