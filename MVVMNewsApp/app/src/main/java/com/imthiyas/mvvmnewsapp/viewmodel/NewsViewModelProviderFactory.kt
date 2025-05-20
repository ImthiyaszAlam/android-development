package com.imthiyas.mvvmnewsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imthiyas.mvvmnewsapp.repository.NewsRepository


class NewsViewModelProviderFactory(
    val app: Application,
    private val newsRepository: NewsRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }

}