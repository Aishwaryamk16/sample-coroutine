package com.example.signinappsample.Database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)

//enties{Note.class}   ->in java
//entities = [Note::class]   -->in kotlin
abstract class NoteRoomdataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        fun getDatabase(context: Application): NoteRoomdataBase? {
             var INSTANCE: NoteRoomdataBase? = null
            //if instance is null create once else return same instance
            if (INSTANCE == null) {
                synchronized(NoteRoomdataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomdataBase::class.java,
                            "word_database"
                        ) // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()

                            //To delete directly whole data, MAin thread UI will be blocked,so add this line.
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
