package com.example.mymovielab2.add

import com.example.mymovielab2.room.LocalDataSource
import com.example.mymovielab2.room.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddMoviePresenter(
    private var viewInterface: AddMovieContract.ViewInterface?,
    private var dataSource: LocalDataSource
) : AddMovieContract.PresenterInterface {

    override fun handleSearchRequest(query: String, year: String) {

        when {
            query.isEmpty() -> viewInterface?.showSearchError("Введите название фильма")
            else -> viewInterface?.openSearchScreen(query, year)
        }
    }

    override fun handleAddMovie(movie: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.insertMovie(movie)
            withContext(Dispatchers.Main) {
                viewInterface?.showAddSuccess()
                viewInterface?.returnToMain()
            }
        }
    }

    fun detachView() {
        viewInterface = null
    }
}
