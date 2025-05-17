package com.imthiyas.mvvmnewsapp.repository


import com.imthiyas.mvvmnewsapp.api.RetrofitInstance
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import java.util.Locale.IsoCountryCode

class NewsRepository(val database: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

}