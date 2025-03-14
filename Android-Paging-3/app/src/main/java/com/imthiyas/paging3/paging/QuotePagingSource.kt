package com.imthiyas.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imthiyas.paging3.model.Result
import com.imthiyas.paging3.retrofit.QuotesAPI

class QuotePagingSource(val quotesAPI: QuotesAPI) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        try {
            val pagePosition = params.key ?: 1
            val quotesResponse = quotesAPI.getQuotes(pagePosition)
            return LoadResult.Page(
                data = quotesResponse.result,
                prevKey = if (pagePosition == 1) null else pagePosition - 1,
                nextKey = if (pagePosition == quotesResponse.totalPage) null else pagePosition + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}