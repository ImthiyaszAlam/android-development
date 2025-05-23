package com.imthiyas.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.imthiyas.mvvmnewsapp.R
import com.imthiyas.mvvmnewsapp.adapters.NewsAdapter
import com.imthiyas.mvvmnewsapp.db.ArticleDatabase
import com.imthiyas.mvvmnewsapp.repository.NewsRepository
import com.imthiyas.mvvmnewsapp.ui.NewsActivity
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModel
import com.imthiyas.mvvmnewsapp.viewmodel.NewsViewModelProviderFactory

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {


    private lateinit var paginationProgressBar: ProgressBar
    private lateinit var rvBreakingNews: RecyclerView
    lateinit var savedNewsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = NewsRepository(ArticleDatabase(requireContext()))
        val factory = NewsViewModelProviderFactory(requireActivity().application, repository)
        savedNewsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]



        paginationProgressBar = requireView().findViewById(R.id.paginationProgressBar)
        rvBreakingNews = requireView().findViewById(R.id.rvBreakingNews)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleNewsFragment, bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                savedNewsViewModel.deleteArticle(article)
                Snackbar.make(view, "Deleted Successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        savedNewsViewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvBreakingNews)
        }

        savedNewsViewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)

        })


    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}