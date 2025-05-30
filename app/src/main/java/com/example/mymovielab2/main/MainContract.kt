package com.example.mymovielab2.main


import com.example.mymovielab2.room.MovieEntity

class MainContract {

    interface PresenterInterface {
        fun getMyMoviesList()
        fun stop()


        fun deleteMovie(movie: MovieEntity)
        fun addMovie(movie: MovieEntity)
        fun showDeleteConfirmation(movie: MovieEntity)

    }

    interface ViewInterface {
        fun  displayMovies (movieList: List <MovieEntity>)
        fun  displayNoMovies ()
        fun  displayMessage (message: String )
        fun  displayError (message: String )

        fun showDeleteConfirmationDialog(movie: MovieEntity)
        fun showUndoSnackbar(movie: MovieEntity)

    }
}
