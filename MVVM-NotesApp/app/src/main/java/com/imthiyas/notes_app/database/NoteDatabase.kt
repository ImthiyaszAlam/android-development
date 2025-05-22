package com.imthiyas.notes_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imthiyas.notes_app.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var databaseInstance: NoteDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = databaseInstance ?: synchronized(LOCK) {
            databaseInstance ?: createDatabase(context).also {
                databaseInstance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "noteDatabase"
            ).build()

    }

}