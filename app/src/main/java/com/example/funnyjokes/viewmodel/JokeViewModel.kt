package com.example.funnyjokes.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.models.JokesResponse
import com.example.funnyjokes.repository.JokeRepository
import com.example.funnyjokes.utils.NetworkResult
import kotlinx.coroutines.*

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {


    private var mutableLiveData = MutableLiveData<NetworkResult<JokesResponse>>()
    val liveData: LiveData<NetworkResult<JokesResponse>>
        get() = mutableLiveData

    private var mutableLiveDataDao = MutableLiveData<List<JokesEntity>>()
    val liveDataDao: LiveData<List<JokesEntity>>
        get() = mutableLiveDataDao


    val coroutineScope: CoroutineScope = viewModelScope


    fun getJokes() {
        mutableLiveData.postValue(NetworkResult.Loading())
        coroutineScope.launch {
            while (true) {
                mutableLiveData.postValue(jokeRepository.getJokes())
                jokeRepository.getJokesDao().observeForever{
                    mutableLiveDataDao.postValue(it)
                }
                delay(10000)
            }
        }

    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }


}