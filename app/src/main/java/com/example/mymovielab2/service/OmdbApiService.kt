package com.example.mymovielab2.service

import com.example.mymovielab2.data.MovieDetail
import com.example.mymovielab2.data.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = "a5922d23",
        @Query("s") search: String,
        @Query("y") year: String? = null
    ): Response<SearchResponse>

    @GET("/")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey: String = "a5922d23",
        @Query("i") imdbID: String,
        @Query("plot") plot: String = "short"
    ): Response<MovieDetail>
}
