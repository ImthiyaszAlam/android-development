package com.imthiyas.mvvmnewsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imthiyas.mvvmnewsapp.Article


@Database(
    entities = [Article::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        
    }
}