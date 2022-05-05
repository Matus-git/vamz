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
     * @param note
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(note:Note)

    /**
     * Funkcia zabezpečuje upravenie poznánky
     *
     * @param note
     */
    @Update
   fun update(note:Note)

    /**
     *Funkcia zabezpečuje odstránenie poznánky
     *
     * @param note
     */
    @Delete
    fun delete(note:Note)

    /**
     *Funkcia zabezpečí získanie všetkých
     * poznámok z databázy.
     *V @Query špecifikujeme aké dáta chceme získať a ako
     * a podľa čoho majú byť dáta usporiadané
     * @return
     */
    @Query("Select * from notes order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    /**
     *Funkcia slúži na vyhľadávanie v našich poznámkach na základe znakov
     * nachádzajúcich sa v nadpise poznámky
     * Vyhľadávanie funguje po znakoch ale aj celých slovách
     *
     * @param searchNote
     * @return
     */
    @Query("Select * from notes where title like :searchNote")
    fun searchNotes(searchNote:String): LiveData<List<Note>>
}