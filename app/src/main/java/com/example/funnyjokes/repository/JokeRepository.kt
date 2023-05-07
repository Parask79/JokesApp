package com.example.funnyjokes.repository

import androidx.lifecycle.LiveData
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.models.JokesResponse
import com.example.funnyjokes.utils.NetworkResult

interface JokeRepository {
    suspend fun getJokes()
    fun getLiveData():LiveData<NetworkResult<List<JokesEntity>>>
}