package com.imthiyas.paging3.paging

import android.util.Log
import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.imthiyas.paging3.db.QuoteDatabase
import com.imthiyas.paging3.model.Result
import com.imthiyas.paging3.retrofit.QuotesAPI

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator(
    private val quotesAPI: QuotesAPI,
    private val quoteDatabase: QuoteDatabase
) : RemoteMediator<Int, Result>() {

    val TAG = "QuoteRemoteMediator"
    val quoteDao = quoteDatabase.quoteDao()
    val quoteRemoteKeysDao = quoteDatabase.remoteKeysDao()


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {
        try {
            val currentPage = 1;
            val apiResponseForPage = quotesAPI.getQuotes(currentPage)
            val paginationEndReach = apiResponseForPage.totalPage == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (paginationEndReach) null else currentPage + 1
            quoteDatabase.withTransaction {
                quoteDao.addQuotes(apiResponseForPage.result)
            }

        } catch (e: Exception) {
            val errorMessage = e.message ?: "Unknown error"
            Log.d(TAG, "errorMessage : $errorMessage")
        }
    }
}