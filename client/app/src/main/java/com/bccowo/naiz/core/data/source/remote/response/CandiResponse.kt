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
    val scanned_relief: Int = 0
)

data class CandiRating(
    val rate: Int
)