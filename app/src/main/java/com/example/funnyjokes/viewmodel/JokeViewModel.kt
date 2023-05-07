package com.example.funnyjokes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.repository.JokeRepository
import com.example.funnyjokes.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {


    val liveData: LiveData<NetworkResult<List<JokesEntity>>>
    get() = jokeRepository.getLiveData()


    fun getJokes() {
        viewModelScope.launch {
            while(true) {
                jokeRepository.getJokes()
                delay(60000)
            }
        }
    }

}