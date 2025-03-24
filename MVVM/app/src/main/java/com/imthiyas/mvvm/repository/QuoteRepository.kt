package com.imthiyas.mvvm.repository

import androidx.lifecycle.LiveData
import com.imthiyas.mvvm.model.Quote
import com.imthiyas.mvvm.model.QuoteDao

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotes(): LiveData<List<Quote>> {
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote) {
        return quoteDao.insertQuote(quote)
    }
}