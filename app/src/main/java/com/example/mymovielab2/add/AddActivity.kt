package com.example.mymovielab2.add


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mymovieapp.databinding.ActivityAddBinding
import androidx.activity.viewModels
import com.example.mymovielab2.room.LocalDataSource
import com.example.mymovielab2.search.SearchActivity
import com.example.mymovielab2.room.MovieEntity
import com.example.mymovielab2.viewModel.MovieViewModel


class AddActivity : AppCompatActivity(), AddMovieContract.ViewInterface {

    private lateinit var binding: ActivityAddBinding
    private val viewModel: MovieViewModel by viewModels()
    private var selectedMovie: MovieEntity? = null

    private lateinit var addMoviePresenter: AddMoviePresenter

    companion object {
        const val REQUEST_CODE_SEARCH = 1001
        const val EXTRA_SELECTED_MOVIE = "selected_movie"
    }

    fun setupPresenter() {
        val dataSource = LocalDataSource(application)
        addMoviePresenter =  AddMoviePresenter(this, dataSource)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPresenter()

        binding.btnSearch.setOnClickListener {
            val query = binding.etMovieName.text.toString().trim()
            val year = binding.etMovieYear.text.toString().trim()
            if (query.isNotEmpty()) {
                addMoviePresenter.handleSearchRequest(query, year)
            } else {
                showSearchError("Введите название фильма")
            }
        }

        binding.btnAddMovie.setOnClickListener {
            selectedMovie?.let {
                addMoviePresenter.handleAddMovie(it)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra(EXTRA_SELECTED_MOVIE)?.let { extra ->
                if (extra is MovieEntity) {
                    showMovieDetails(extra)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun openSearchScreen(query: String, year: String) {
        val intent = Intent(this, SearchActivity::class.java).apply {
            putExtra("query", query)
            putExtra("year", year)
        }
        startActivityForResult(intent, REQUEST_CODE_SEARCH)
    }


    override fun showMovieDetails(movie: MovieEntity) {
        selectedMovie = movie
        binding.etMovieName.setText(movie.title)
        binding.etMovieYear.setText(movie.year)
        binding.imgPoster.visibility = android.view.View.VISIBLE
        Glide.with(this).load(movie.posterUrl).into(binding.imgPoster)
        binding.btnAddMovie.visibility = android.view.View.VISIBLE
    }

    override fun showSearchError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showAddSuccess() {
        Toast.makeText(this, "Фильм добавлен!", Toast.LENGTH_SHORT).show()
    }

    override fun returnToMain() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        addMoviePresenter.detachView()
    }


}