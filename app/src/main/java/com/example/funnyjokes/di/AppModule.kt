package com.example.funnyjokes.di


import com.example.funnyjokes.repository.JokeRepository
import com.example.funnyjokes.repository.JokeRepositoryImpl
import com.example.funnyjokes.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory<JokeRepository> {
        JokeRepositoryImpl(get(), get())
    }

    viewModel {
        JokeViewModel(get())
    }

}



