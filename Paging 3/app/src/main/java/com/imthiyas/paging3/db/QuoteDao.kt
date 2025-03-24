package com.imthiyas.paging3.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imthiyas.paging3.model.Result


@Dao
interface QuoteDao {

    @Query("SELECT * FROM QuoteResultTable ")
    fun getQuotes(): PagingSource<Int, Result>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<Result>)


    @Query("DELETE FROM QuoteResultTable")
    suspend fun deleteQuote(quotes: Result)
}