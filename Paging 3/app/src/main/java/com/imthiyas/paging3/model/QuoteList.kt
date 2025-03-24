package com.imthiyas.paging3.model

data class QuoteList(
    val count:Int,
    val lastItemIndex:Int,
    val page:Int,
    val result:List<Result>,
    val totalCount:Int,
    val totalPage:Int
)
