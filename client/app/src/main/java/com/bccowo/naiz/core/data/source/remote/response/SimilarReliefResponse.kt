package com.bccowo.naiz.core.data.source.remote.response

data class SimilarReliefResponse(
    val data: List<SimilarRelief>
)

data class SimilarRelief(
    val similar: List<Relief>
)