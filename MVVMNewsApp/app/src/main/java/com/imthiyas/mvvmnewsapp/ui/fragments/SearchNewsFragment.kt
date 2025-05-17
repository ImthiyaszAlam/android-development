package com.imthiyas.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imthiyas.mvvmnewsapp.R
import com.imthiyas.mvvmnewsapp.adapters.NewsAdapter
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.repository.NewsRepository
import com.imthiyas.mvvmnewsapp.ui.NewsActivity
import com.imthiyas.mvvmnewsapp.util.Resource
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModel
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModelProviderFactory
import kotlinx.coroutines.Job

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    val TAG = "BNF"
    private lateinit var paginationProgressBar: ProgressBar
    private lateinit var rvBreakingNews: RecyclerView
    private lateinit var searchView: SearchView
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = NewsRepository(ArticleDatabase(requireContext()))
        val factory = NewsViewModelProviderFactory(repository)
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        paginationProgressBar = requireView().findViewById(R.id.paginationProgressBar)
        rvBreakingNews = requireView().findViewById(R.id.rvBreakingNews)
        searchView = requireView().findViewById(R.id.searchView)
        setupRecyclerView()
        val job: Job? = null

        newsViewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }


        })

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}