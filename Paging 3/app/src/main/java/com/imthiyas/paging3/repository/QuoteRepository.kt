package com.imthiyas.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.imthiyas.paging3.paging.QuotePagingSource
import com.imthiyas.paging3.retrofit.QuotesAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quotesAPI: QuotesAPI) {
    fun getQuotes() = Pager(
        config = PagingConfig(20, 100),
        pagingSourceFactory = { QuotePagingSource(quotesAPI) }
    ).liveData
}