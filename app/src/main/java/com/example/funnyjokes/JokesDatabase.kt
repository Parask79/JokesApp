package com.example.funnyjokes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.funnyjokes.dao.JokeDAO
import com.example.funnyjokes.models.JokesEntity


@Database(entities = [JokesEntity::class] , version = 1)
abstract class JokesDatabase :RoomDatabase(){
 abstract fun jokeDao(): JokeDAO
}