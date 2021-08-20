package com.bccowo.naiz

import android.app.Application
import com.bccowo.naiz.core.di.mainModule
import com.bccowo.naiz.core.di.networkModule
import com.bccowo.naiz.core.di.storageModule
import com.bccowo.naiz.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                mainModule, viewModelModule, networkModule, storageModule
            )
        }
    }
}