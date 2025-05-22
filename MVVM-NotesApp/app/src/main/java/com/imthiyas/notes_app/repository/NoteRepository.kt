package com.imthiyas.notes_app.repository

import androidx.room.Query
import com.imthiyas.notes_app.database.NoteDatabase
import com.imthiyas.notes_app.model.Note

class NoteRepository(private val noteDatabase: NoteDatabase) {

    suspend fun addNote(note: Note) = noteDatabase.getNoteDao().addNotes(note)

    suspend fun updateNote(note: Note) = noteDatabase.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = noteDatabase.getNoteDao().deleteNote(note)

    fun getAllNotes() = noteDatabase.getNoteDao().getAllNotes()

    fun searchNote(query: String) = noteDatabase.getNoteDao().searchNote(query)

}