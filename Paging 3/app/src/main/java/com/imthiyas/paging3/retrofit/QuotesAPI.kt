package com.imthiyas.paging3.retrofit

import com.imthiyas.paging3.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {

    @GET("/quotes/")
    suspend fun getQuotes(@Query("page") page: Int): QuoteList
}