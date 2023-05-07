package com.example.funnyjokes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.funnyjokes.JokesDatabase
import com.example.funnyjokes.api.JokeAPI
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.utils.NetworkResult

class JokeRepositoryImpl(private val jokeAPI: JokeAPI, private val database: JokesDatabase) :
    JokeRepository {

    private val _mutableLiveData = MutableLiveData<NetworkResult<List<JokesEntity>>>()

    override suspend fun getJokes() {
        val response = jokeAPI.getJokes()
        _mutableLiveData.postValue(NetworkResult.Loading())
        if (response.isSuccessful && response.body() != null) {
            if (database.jokeDao().getID() >= 10) {
                database.jokeDao().deleteID()
            }
            database.jokeDao().insertJoke(JokesEntity(0, response.body()!!.joke))

            database.jokeDao().getJokes().observeForever()
            {
                it?.let {
                    _mutableLiveData.postValue(NetworkResult.Success(it))
                }
            }

        } else if (response.errorBody() != null) {
            _mutableLiveData.postValue(NetworkResult.Error("Something Went wrong"))
        }

    }

    override fun getLiveData(): LiveData<NetworkResult<List<JokesEntity>>> {
        return _mutableLiveData
    }
}