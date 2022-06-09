package com.itc.recycleviewroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itc.recycleviewroomdatabase.model.Event

@Dao
interface EventDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Query("SELECT * FROM event_table ORDER BY id ASC")
    fun readAll(): LiveData<List<Event>>

}