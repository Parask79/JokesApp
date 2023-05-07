package com.example.funnyjokes.di

import com.example.funnyjokes.api.JokeAPI
import com.example.funnyjokes.utils.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val remoteDataSource = module {
    single { createWebService<JokeAPI>(BASE_URL) }
}


inline fun <reified T> createWebService(url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(T::class.java)
}