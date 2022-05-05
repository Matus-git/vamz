package com.example.semkanotes.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Trieda predstavuje ako vyzerá databázová tabuľka
 * a z akých parametrov sa skladá
 *
 * @property titleNote predstavuje stĺpec ktorý uchováva hodnoty o nadpise
 * @property descNote predstavuje stĺpec ktorý uchováva hodnoty o popise poznámky
 * @property timeStamp predstavuje stĺpec v kotorm sa uloží dátum manipulácie s poznámkou
 */

@Entity(tableName="notes")
data class Note (@ColumnInfo(name = "title")val titleNote:String,
            @ColumnInfo(name = "description")val descNote:String,
            @ColumnInfo(name = "timestamp")val timeStamp:String
            ){
    @PrimaryKey(autoGenerate = true)
    var id = 0

}