package com.example.funnyjokes.api

import com.example.funnyjokes.models.JokesResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeAPI {
    @GET("/api?format=json")
   suspend fun getJokes():Response<JokesResponse>
}