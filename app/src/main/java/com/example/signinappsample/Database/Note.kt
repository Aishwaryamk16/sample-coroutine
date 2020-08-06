package com.example.signinappsample.Database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
//tBlename is case sensitive
/*class Word(@NonNull word: String) {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private val mWord: String
    fun getWord(): String {
        return mWord
    }

    init {
        mWord = word
    }
}*/

class Note(
    @PrimaryKey @NonNull val id: String,
    @ColumnInfo(name = "firstColumnName")
    val note: String
) {
    fun getNoteValueAdded(): String {
        return note
    }

    fun getAddedId(): String {
        return id
    }
}