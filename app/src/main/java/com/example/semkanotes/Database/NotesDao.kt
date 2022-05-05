package com.example.semkanotes.Database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Rozhranie NotesDao špecifikuje SQL queries nad NoteDatabase
 * ktoré priradí k rozne volaným metodám taktiež zabezpečuje
 * pridávanie, mazanie a upravovanie
 *
 */

@Dao
interface NotesDao {

    /**
     * Funkcia zabezpečuje vloženie novej poznánky
     *
     * @param note typu Note
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(note:Note)

    /**
     * Funkcia zabezpečuje upravenie poznánky
     *
     * @param note typu Note
     */
    @Update
   fun update(note:Note)

    /**
     *Funkcia zabezpečuje odstránenie poznánky
     *
     * @param note typu Note
     */
    @Delete
    fun delete(note:Note)

    /**
     *Funkcia zabezpečí získanie všetkých
     * poznámok z databázy.
     *V @Query špecifikujeme aké dáta chceme získať a ako
     * a podľa čoho majú byť dáta usporiadané
     *
     * @return LiveData<List<Note>>
     */
    @Query("Select * from notes order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    /**
     *Funkcia slúži na vyhľadávanie v našich poznámkach na základe znakov
     * nachádzajúcich sa v nadpise poznámky
     * Vyhľadávanie funguje po znakoch ale aj celých slovách
     *
     * @param searchNote typu String
     * @return LiveData<List<Note>>
     */
    @Query("Select * from notes where title like :searchNote")
    fun searchNotes(searchNote:String): LiveData<List<Note>>
}