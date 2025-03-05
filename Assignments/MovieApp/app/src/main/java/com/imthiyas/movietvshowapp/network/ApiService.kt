package com.imthiyas.movietvshowapp.network

import com.imthiyas.movietvshowapp.model.ApiResponse
import com.imthiyas.movietvshowapp.model.Movie
import com.imthiyas.movietvshowapp.model.TVShow
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/list-titles")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("types") type: String = "movie"
    ): Single<ApiResponse<Movie>>

    @GET("v1/list-titles")
    fun getTVShows(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "tv_series"
    ): Single<ApiResponse<TVShow>>


}