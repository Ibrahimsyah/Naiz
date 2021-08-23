package com.bccowo.naiz.core.data.source.remote

import com.bccowo.naiz.core.data.source.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NaizMLApi {
    @Multipart
    @POST("/relief_image/predict")
    suspend fun predictImage(@Part image: MultipartBody.Part): PredictResponse
}