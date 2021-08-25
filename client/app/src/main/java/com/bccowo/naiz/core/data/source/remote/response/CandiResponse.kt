package com.bccowo.naiz.core.data.source.remote.response

data class CandiResponseBody(
    val data: List<CandiResponse>
)

data class CandiResponse(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val rating: List<CandiRating>,
    val total_reliefs: Int,
    val reliefs: List<Reliefs>,
    val scanned_relief: Int = 0
)

data class Reliefs(
    val id: Int,
    val detail: ReliefDetail
)

data class ReliefDetail(
    val description: String,
    val image: String,
    val name: String,
    val type: ReliefType
)

data class ReliefType(
    val name: String
)

data class CandiRating(
    val rate: Int
)