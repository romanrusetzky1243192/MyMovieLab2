package com.example.mymovielab2.add

import com.example.mymovielab2.room.MovieEntity

class  AddMovieContract  {
    interface  PresenterInterface  {
        fun handleSearchRequest(query: String, year: String)
        fun handleAddMovie(movie: MovieEntity)
    }

    interface  ViewInterface  {
        fun  returnToMain ()

        fun showMovieDetails(movie: MovieEntity)
        fun showSearchError(message: String)
        fun showAddSuccess()
        fun openSearchScreen(query: String, year: String)

    }
}
