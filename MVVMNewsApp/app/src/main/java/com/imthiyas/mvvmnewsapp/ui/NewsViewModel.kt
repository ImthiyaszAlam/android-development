package com.imthiyas.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.room.Dao
import com.imthiyas.mvvmnewsapp.repository.NewsRepository

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

}