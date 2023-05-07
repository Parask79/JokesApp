package com.example.funnyjokes

import android.app.Application
import com.example.funnyjokes.di.appModule
import com.example.funnyjokes.di.databaseModule
import com.example.funnyjokes.di.remoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, databaseModule, remoteDataSource)
        }
    }
}