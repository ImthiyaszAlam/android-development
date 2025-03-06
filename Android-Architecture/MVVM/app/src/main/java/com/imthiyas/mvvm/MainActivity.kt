package com.imthiyas.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imthiyas.mvvm.databinding.ActivityMainBinding
import com.imthiyas.mvvm.model.Quote
import com.imthiyas.mvvm.model.QuoteDao
import com.imthiyas.mvvm.model.QuoteDatabase
import com.imthiyas.mvvm.repository.QuoteRepository
import com.imthiyas.mvvm.viewmodel.QuoteViewModel
import com.imthiyas.mvvm.viewmodel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val quoteDao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val quoteRepo = QuoteRepository(quoteDao)
        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModelFactory(quoteRepo)
        ).get(QuoteViewModel::class.java)

        quoteViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })
        binding.btnAdd.setOnClickListener {
            val quote = Quote(0, "This is testing", "Testing")
            quoteViewModel.insertQuote(quote)
        }

    }
}