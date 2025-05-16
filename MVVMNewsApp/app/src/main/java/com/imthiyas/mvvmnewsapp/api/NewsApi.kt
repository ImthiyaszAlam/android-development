package com.imthiyas.mvvmnewsapp.api

import com.imthiyas.mvvmnewsapp.db.models.NewsResponse
import com.imthiyas.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        page: Int = 1,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int = 1,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<NewsResponse>


}