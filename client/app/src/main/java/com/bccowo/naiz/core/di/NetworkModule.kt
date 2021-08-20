package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.config.Network
import com.bccowo.naiz.core.data.source.remote.NaizApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Network.BASE_URL)
            .build()
    }

    single<NaizApi> { get<Retrofit>().create(NaizApi::class.java) }
}