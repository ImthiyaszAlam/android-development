package com.imthiyas.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imthiyas.paging3.model.Result
import com.imthiyas.paging3.retrofit.QuotesAPI

class QuotePagingSource(val quotesAPI: QuotesAPI) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        TODO("Not yet implemented")
    }
}