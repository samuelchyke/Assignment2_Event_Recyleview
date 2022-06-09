package com.itc.recycleviewroomdatabase.data

import androidx.lifecycle.LiveData
import com.itc.recycleviewroomdatabase.model.Event

class EventRepository (
    private val eventDao: EventDAO
        ){

    val readAll: LiveData<List<Event>> = eventDao.readAll()

    suspend fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    suspend fun updateEvent(event: Event){
        eventDao.updateEvent(event)
    }



}