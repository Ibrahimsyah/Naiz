package com.bccowo.naiz.core.data.source.remote.response

data class ReliefListResponse(
    val data: List<ReliefListWrapper>
)

data class ReliefListWrapper(
    val reliefs: List<Relief>
)

data class Relief(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val type: ReliefType
)

data class ReliefType(
    val name: String
)