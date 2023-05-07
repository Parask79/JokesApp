package com.example.funnyjokes.di

import androidx.room.Room
import com.example.funnyjokes.JokesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(), JokesDatabase::class.java, "jokesDB"
        ).build()
    }
}