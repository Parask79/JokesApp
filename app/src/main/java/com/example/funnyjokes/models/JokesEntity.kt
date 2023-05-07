package com.example.funnyjokes.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jokes_table")
data class JokesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val joke: String
)

