package com.imthiyas.mvvmnewsapp.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.AbsSavedState
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imthiyas.mvvmnewsapp.R
import com.imthiyas.mvvmnewsapp.adapters.NewsAdapter
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.db.models.NewsResponse
import com.imthiyas.mvvmnewsapp.repository.NewsRepository
import com.imthiyas.mvvmnewsapp.ui.NewsActivity
import com.imthiyas.mvvmnewsapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.imthiyas.mvvmnewsapp.util.Resource
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModel
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModelProviderFactory

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "BNF"
    private lateinit var paginationProgressBar: ProgressBar
    private lateinit var rvBreakingNews: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = NewsRepository(ArticleDatabase(requireContext()))
        val factory = NewsViewModelProviderFactory(requireActivity().application, repository)
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        paginationProgressBar = requireView().findViewById(R.id.paginationProgressBar)
        rvBreakingNews = requireView().findViewById(R.id.rvBreakingNews)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleNewsFragment, bundle
            )
        }

        newsViewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = newsViewModel.breakingNewsPage == totalPages
                        if (isLastPage) {
                            rvBreakingNews.setPadding(0, 0, 0, 0)
                        }
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
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstIemVisiblePosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount


            val isNotLoadingAndNotOnLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstIemVisiblePosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstIemVisiblePosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLoadingAndNotOnLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                newsViewModel.getBreakingNews("us")
                isScrolling = false
            }

        }
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }


}