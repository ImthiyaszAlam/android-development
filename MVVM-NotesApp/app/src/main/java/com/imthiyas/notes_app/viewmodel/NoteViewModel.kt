package com.imthiyas.notes_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.imthiyas.notes_app.model.Note
import com.imthiyas.notes_app.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val application: Application, private val noteRepository: NoteRepository) :
    AndroidViewModel(application) {

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun getAllNotes():LiveData<List<Note>> {
      return  noteRepository.getAllNotes()
    }

    fun searchNotes(query: String?):LiveData<List<Note>> {
       return noteRepository.searchNote(query)
    }
}