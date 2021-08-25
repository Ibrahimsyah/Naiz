package com.bccowo.naiz.core.data.source.remote.response

data class SimillarReliefResponse(
    val data: List<SimilarRelief>
)

data class SimilarRelief(
    val similar: List<Relief>
)

data class Relief(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
)