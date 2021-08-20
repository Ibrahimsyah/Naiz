package com.bccowo.naiz.core.di

import android.content.Context
import androidx.room.Room
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.core.config.Storage
import com.bccowo.naiz.core.data.source.local.LocalDataSource
import com.bccowo.naiz.core.data.source.local.NaizDb
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single { LocalDataSource(get()) }
    single { Room.databaseBuilder(androidContext(), NaizDb::class.java, Storage.DB_NAME).build() }
    single { get<NaizDb>().naizDao() }
    single {
        androidApplication().getSharedPreferences(
            SharedPreference.GLOBAL_PREF,
            Context.MODE_PRIVATE
        )
    }
}