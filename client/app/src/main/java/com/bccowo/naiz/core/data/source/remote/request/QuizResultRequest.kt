package com.bccowo.naiz.core.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class QuizResultRequest(
    @SerializedName("quiz_pack_id")
    val quizId: Int,
    val result: Int
)