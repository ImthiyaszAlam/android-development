package com.imthiyas.paging3.paging

import android.util.Log
import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.imthiyas.paging3.db.QuoteDatabase
import com.imthiyas.paging3.model.QuoteRemoteKeys
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
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {}

                //Scroll up
                LoadType.APPEND -> {}

                //Scroll down
                LoadType.PREPEND -> {}
            }
            val apiResponseForPage = quotesAPI.getQuotes(currentPage)
            val paginationEndReach = apiResponseForPage.totalPage == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (paginationEndReach) null else currentPage + 1
            quoteDatabase.withTransaction {
                quoteDao.addQuotes(apiResponseForPage.result)
                val keys = apiResponseForPage.result.map { quote ->
                    QuoteRemoteKeys(
                        id = quote._id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                quoteRemoteKeysDao.addAllRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}