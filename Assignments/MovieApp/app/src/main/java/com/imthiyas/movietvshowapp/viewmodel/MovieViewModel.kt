package com.imthiyas.movietvshowapp.viewmodel

import androidx.lifecycle.ViewModel
import com.imthiyas.movietvshowapp.model.Movie
import com.imthiyas.movietvshowapp.model.TVShow
import com.imthiyas.movietvshowapp.repository.MovieRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _tvShows = MutableStateFlow<List<TVShow>>(emptyList())
    val tvShows: StateFlow<List<TVShow>> get() = _tvShows

    private val disposable = CompositeDisposable()
    private val apiKey = "VqUbhaftEnd0b1EOrQbI9J8d3F0YClaH3qLsyPl7"

    init {
        fetchMoviesAndTVShows()
    }

    private fun fetchMoviesAndTVShows() {
        disposable.add(
            repository.fetchMoviesAndTvShows(apiKey).subscribeBy(
                onSuccess = { (movies, tvShows) ->
                    _movies.value = movies
                    _tvShows.value = tvShows
                },
                onError = { error ->
                    println("Error fetching data: ${error.message}")
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}