package com.bccowo.naiz.core.data.source.remote.response

data class CandiScanCountResponse(
    val data: List<Data>
)

data class Data(
    val count: Int
)