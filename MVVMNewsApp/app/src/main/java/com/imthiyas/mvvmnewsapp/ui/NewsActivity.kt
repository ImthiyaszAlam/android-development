package com.imthiyas.mvvmnewsapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imthiyas.mvvmnewsapp.R
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {


    lateinit var newsViewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val navController = findNavController(R.id.newsNavHostFragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigationView)
        bottomNav.setupWithNavController(navController)

    }
}