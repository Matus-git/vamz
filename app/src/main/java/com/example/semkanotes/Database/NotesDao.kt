package com.example.semkanotes.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(note:Note)

    @Update
   fun update(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("Select * from notes order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Select * from notes where title like :searchNote")
    fun searchNotes(searchNote:String): LiveData<List<Note>>
}