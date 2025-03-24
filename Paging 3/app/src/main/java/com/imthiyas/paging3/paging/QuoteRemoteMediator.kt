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
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysForCurrentItem(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                //New Data  up
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }

                //Loaded previous data
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
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

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Result>): QuoteRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { quote ->
            quoteRemoteKeysDao.getRemoteKeys(id = quote._id)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Result>): QuoteRemoteKeys? {
        return state.pages.firstOrNull() {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { quote ->
            quoteRemoteKeysDao.getRemoteKeys(id = quote._id)
        }
    }

    private suspend fun getRemoteKeysForCurrentItem(state: PagingState<Int, Result>): QuoteRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?._id?.let { id ->
                quoteRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }
}