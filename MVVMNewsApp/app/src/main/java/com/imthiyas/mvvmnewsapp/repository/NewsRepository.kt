package com.imthiyas.mvvmnewsapp.repository


import com.imthiyas.mvvmnewsapp.api.RetrofitInstance
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.db.models.Article
import java.util.Locale.IsoCountryCode

class NewsRepository(val database: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)


    /*  Room DB Operations*/

    suspend fun upsert(article: Article) = database.getArticleDao().upsert(article)
    fun getSavedNews() = database.getArticleDao().getAllArticles()
    suspend fun deleteArticle(article: Article) = database.getArticleDao().deleteArticle(article)

}