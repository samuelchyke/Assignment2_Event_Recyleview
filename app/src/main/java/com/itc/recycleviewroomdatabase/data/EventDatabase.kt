package com.itc.recycleviewroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itc.recycleviewroomdatabase.model.Event

@Database(entities = [Event::class], version = 1, exportSchema = false )
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventsDao(): EventDAO

    companion object{

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            val tempInstance = INSTANCE
                if (tempInstance != null){
                    return tempInstance
                }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}