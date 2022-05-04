package com.example.semkanotes.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="notes")
data class Note (@ColumnInfo(name = "title")val titleNote:String,
            @ColumnInfo(name = "description")val descNote:String,
            @ColumnInfo(name = "timestamp")val timeStamp:String
            ){
    @PrimaryKey(autoGenerate = true)
    var id = 0

}