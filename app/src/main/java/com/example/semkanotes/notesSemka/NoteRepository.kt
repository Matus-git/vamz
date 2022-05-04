package com.example.semkanotes.notesSemka

import androidx.lifecycle.LiveData
import com.example.semkanotes.Database.Note
import com.example.semkanotes.Database.NotesDao

class NoteRepository(private val notesDao: NotesDao) {


    suspend fun insert(note:Note){
        notesDao.insert(note)
    }

    suspend fun update(note:Note){
        notesDao.update(note)
    }

    suspend fun delete(note:Note){
        notesDao.delete(note)
    }

    fun searchDatabase(searchNote:String) : LiveData<List<Note>>{
        return notesDao.searchNotes(searchNote)
    }

    fun getAllData() : LiveData<List<Note>>{
        return notesDao.getAllNotes()
    }


}