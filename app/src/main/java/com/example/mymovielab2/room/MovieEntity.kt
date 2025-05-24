package com.example.mymovielab2.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val imdbID: String,
    val title: String,
    val year: String,
    val posterUrl: String
):java.io.Serializable