package com.imthiyas.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.imthiyas.mvvmnewsapp.R
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.repository.NewsRepository
import com.imthiyas.mvvmnewsapp.ui.NewsActivity
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModel
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModelProviderFactory

class ArticleNewsFragment : Fragment(R.layout.fragment_article_news) {

    lateinit var newsViewModel: NewsViewModel
    val args: ArticleNewsFragmentArgs by navArgs()
    lateinit var webView: WebView
    private lateinit var fab: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = requireView().findViewById<WebView>(R.id.webView)
        fab = requireView().findViewById<FloatingActionButton>(R.id.fab)

        val repository = NewsRepository(ArticleDatabase(requireContext()))
        val factory = NewsViewModelProviderFactory(repository)
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]


        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
        fab.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_LONG).show()
        }

    }
}