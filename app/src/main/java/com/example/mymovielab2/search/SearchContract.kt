package com.example.mymovielab2.search

import com.example.mymovielab2.data.MovieDetail
import com.example.mymovielab2.room.MovieEntity

class SearchContract {

    interface PresenterInterface {
        fun searchMovies(query: String, year: String?)
        fun stop()
    }

    interface ViewInterface {
        fun showSearchResults(movies: List<MovieEntity>)
        fun showError(message: String)
        fun returnSelectedMovie(movie: MovieEntity)
    }
}
