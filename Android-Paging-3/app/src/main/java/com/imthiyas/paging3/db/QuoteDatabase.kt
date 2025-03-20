package com.imthiyas.paging3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imthiyas.paging3.model.QuoteRemoteKeys


@Database([Result::class, QuoteRemoteKeys::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}