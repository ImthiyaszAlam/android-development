package com.imthiyas.mvvmnewsapp

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)