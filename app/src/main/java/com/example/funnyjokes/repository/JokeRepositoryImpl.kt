package com.example.funnyjokes.repository

import androidx.lifecycle.LiveData
import com.example.funnyjokes.JokesDatabase
import com.example.funnyjokes.api.JokeAPI
import com.example.funnyjokes.base.BaseRepo
import com.example.funnyjokes.models.JokesEntity
import com.example.funnyjokes.models.JokesResponse
import com.example.funnyjokes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers


class JokeRepositoryImpl(private val jokeAPI: JokeAPI, private val database: JokesDatabase) :
    JokeRepository, BaseRepo() {



    override suspend fun getJokes(): NetworkResult<JokesResponse>{
        val response:NetworkResult<JokesResponse> = safeCall(Dispatchers.IO) {
            jokeAPI.getJokes()
        }
        response.data?.let {
            if (database.jokeDao().getID() >= 10) {
                database.jokeDao().deleteID()
            }
            database.jokeDao().insertJoke(JokesEntity(0, it.joke))
        }
        return response
    }

    override fun getJokesDao(): LiveData<List<JokesEntity>> {
        return  database.jokeDao().getJokes()
    }


}