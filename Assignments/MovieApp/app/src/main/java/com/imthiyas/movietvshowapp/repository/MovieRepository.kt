package com.imthiyas.movietvshowapp.repository

import com.imthiyas.movietvshowapp.model.Movie
import com.imthiyas.movietvshowapp.model.TVShow
import com.imthiyas.movietvshowapp.network.ApiService
import io.reactivex.rxjava3.core.Single

class MovieRepository(private val apiService: ApiService) {
    fun fetchMoviesAndTvShows(apiKey: String): Single<Pair<List<Movie>, List<TVShow>>> {
        val movies = apiService.getMovies(apiKey)
        val tvShows = apiService.getTVShows(apiKey)

        return Single.zip(movies, tvShows) { movieResponse, TvResponse ->
            Pair(movieResponse.results, TvResponse.results)
        }
    }
}