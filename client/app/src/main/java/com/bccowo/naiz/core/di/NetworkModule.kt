package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.config.Network
import com.bccowo.naiz.core.data.source.remote.NaizApi
import com.bccowo.naiz.core.data.source.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { RemoteDataSource(get()) }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Network.BASE_URL)
            .client(get())
            .build()
    }

    single<NaizApi> { get<Retrofit>().create(NaizApi::class.java) }
}