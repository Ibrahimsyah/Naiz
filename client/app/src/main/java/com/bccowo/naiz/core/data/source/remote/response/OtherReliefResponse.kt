package com.bccowo.naiz.core.data.source.remote.response

data class OtherReliefResponse(
    val data: List<OtherReliefCandi>
)

data class OtherReliefCandi(
    val other: List<OtherRelief>
)

data class OtherRelief(
    val relief: Relief
)