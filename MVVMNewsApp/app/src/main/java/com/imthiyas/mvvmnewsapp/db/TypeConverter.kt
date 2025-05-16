package com.imthiyas.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.imthiyas.mvvmnewsapp.db.models.Source

class TypeConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}