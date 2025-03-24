package com.imthiyas.paging3

import android.content.Context
import androidx.room.Room
import com.imthiyas.paging3.db.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return Room.databaseBuilder(context, QuoteDatabase::class.java, "QuoteDB").build()
    }
}