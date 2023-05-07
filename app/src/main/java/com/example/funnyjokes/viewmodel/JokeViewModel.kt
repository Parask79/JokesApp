package com.example.funnyjokes.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.repository.JokeRepository
import com.example.funnyjokes.utils.NetworkResult
import kotlinx.coroutines.*

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {


    val liveData: LiveData<NetworkResult<List<JokesEntity>>>
        get() = jokeRepository.getLiveData()
     val coroutineScope: CoroutineScope = viewModelScope


    fun getJokes() {
        coroutineScope.launch {
            while (true) {
                jokeRepository.getJokes()
                delay(60000)
            }
        }

    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }


}