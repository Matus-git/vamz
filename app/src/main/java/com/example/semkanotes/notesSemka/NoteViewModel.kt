package com.example.semkanotes.notesSemka

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.semkanotes.Database.Note
import com.example.semkanotes.Database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Trieda slúži na prevednie dát z databázy do užívateľského rozhrania
 * Predstavuje komunikačńu vrstvu medzi repozitárom a užívateľským rozhraním
 *
 * @param application
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val notes: LiveData<List<Note>>
    val repository: NoteRepository


    init {
        val notesDao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(notesDao)
        notes = repository.getAllNotes()


    }

    /**
     * Funkcia volá delete funkciu s repozitára,
     * pomocou kotrej odsráni záznam z databázy
     *
     * @param note
     */
    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    /**
     * Funkcia volá update funkciu s repozitára,
     * pomocou kotrej upraví záznam v databáze
     *
     * @param note
     */
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    /**
     * Funkcia volá insert funkciu s repozitára,
     * pomocou kotrej pridá nový záznam z databázy
     *
     * @param note
     */
    fun addNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    /**
     * Funkcia zavolá z repozitára funkciu
     * searchDatabase pomocou ktorej vyhľadá dáta
     * @param search typu String
     * @return LiveData<List<Note>>
     */
    fun searchNote(search:String) :LiveData<List<Note>>  {
        return repository.searchDatabase(search)
    }

}