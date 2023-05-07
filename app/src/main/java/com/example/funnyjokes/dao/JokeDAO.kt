package com.example.funnyjokes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.funnyjokes.models.JokesEntity


@Dao
interface JokeDAO {

    @Insert
    suspend fun insertJoke(joke: JokesEntity)

    @Query("SELECT * FROM jokes_table")
    fun getJokes(): LiveData<List<JokesEntity>>

    @Query("SELECT COALESCE(MAX(id), 0) FROM jokes_table")
    suspend fun getID(): Int

    @Query("DELETE FROM jokes_table WHERE id = (SELECT MIN(id) FROM jokes_table)")
    suspend fun deleteID()

}