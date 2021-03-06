package com.example.semkanotes.notesSemka

import androidx.lifecycle.LiveData
import com.example.semkanotes.Database.Note
import com.example.semkanotes.Database.NotesDao

/**
 * Trieda predstavuje repozitár ktorý zabezpečuje získavanie dát
 * V našej aplikácie repozitár získava dáta z Room databázy.
 *
 * @property notesDao
 */

class NoteRepository(private val notesDao: NotesDao) {

    /**
     * Funckia insert slúži na pridanie nového záznamu do databázy
     *
     * @param note typu Note
     */
    suspend fun insert(note:Note){
        notesDao.insert(note)
    }

    /**
     * Funkcia update slúži na upravenie záznamu v databáze
     *
     * @param note typu Note
     */
    suspend fun update(note:Note){
        notesDao.update(note)
    }

    /**
     * Funkcia delete slúži na odstránenie záznamu z databázy
     *
     * @param note typu Note
     */
    suspend fun delete(note:Note){
        notesDao.delete(note)
    }

    /**
     * Funkcia slúži na získanie hľadaných dát z databázy
     *
     * @param searchNote typu String
     * @return LiveData<List<Note>>
     */
    fun searchDatabase(searchNote:String) : LiveData<List<Note>>{
        return notesDao.searchNotes(searchNote)
    }

    /**
     * Funkcia slúži na získanie všetkých dát z databázy
     *
     * @return LiveData<List<Note>>
     */
    fun getAllNotes() : LiveData<List<Note>>{
        return notesDao.getAllNotes()
    }


}