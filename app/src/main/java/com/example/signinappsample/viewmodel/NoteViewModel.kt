package com.example.signinappsample.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.signinappsample.Database.Note
import com.example.signinappsample.Database.NoteDao
import com.example.signinappsample.Database.NoteRoomdataBase

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var notedao: NoteDao? = null
    private var noteDataBase: NoteRoomdataBase? = null

    private var addedNote: LiveData<List<Note>>? = null

    private lateinit var deleteAllNote: Unit

    init {
        noteDataBase =
            NoteRoomdataBase.getDatabase(getApplication())
        notedao = noteDataBase?.noteDao()
        //will be automatically done in BG thread.coz of query.
        addedNote = notedao?.getAllNotes()

    }

    fun deleteAll(): Unit? {
        deleteAllNote = notedao!!.deleteAll()
        return deleteAllNote
    }

    fun deleteById(id:String){
        notedao?.deleteById(id)
    }


    fun getAllNote(): LiveData<List<Note>>? {
        return addedNote
    }

    fun insert(note: Note) {
        //Create an asynctask to insert in background.
        notedao?.let { InsertAsyntask(
            it
        ).execute(note) }

    }

    private class InsertAsyntask internal constructor(dao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        private val mAsyncTaskDao: NoteDao = dao

        //insert operation will be done bg thread.
        override fun doInBackground(vararg note: Note?): Void? {
            note[0]?.let { mAsyncTaskDao.insert(it) }
            return null
        }
    }

    override fun onCleared() {
        super.onCleared()

    }

}