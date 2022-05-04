package com.example.semkanotes.notesSemka

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.semkanotes.Database.Note
import com.example.semkanotes.Database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val notesDao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    fun addNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}