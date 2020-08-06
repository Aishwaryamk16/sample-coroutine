package com.example.signinappsample.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    //write queries in form of funcyions to do all operations

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    //selects everything
    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<Note>>


    //Delete on id
    @Query("DELETE FROM note WHERE id = :id")
    fun deleteById(id: String)


    //delete whole entries
    @Query("DELETE FROM note")
     fun deleteAll(): Unit


    //update on id
 /*   @Query("SELECT * FROM note WHERE id = :id")
    fun editById(id: String)*/
}