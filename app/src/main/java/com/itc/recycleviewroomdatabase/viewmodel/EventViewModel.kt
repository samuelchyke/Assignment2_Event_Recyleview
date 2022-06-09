package com.itc.recycleviewroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.itc.recycleviewroomdatabase.data.EventDatabase
import com.itc.recycleviewroomdatabase.data.EventRepository
import com.itc.recycleviewroomdatabase.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(
    application: Application) :
    AndroidViewModel(application){

        val readAll: LiveData<List<Event>>
        private val eventRepository : EventRepository

        init {
            val eventDao = EventDatabase.getDatabase(application).eventsDao()
            eventRepository = EventRepository(eventDao)
            readAll = eventRepository.readAll
            }

    fun addEvent(event: Event){
        viewModelScope.launch (Dispatchers.IO){
            eventRepository.addEvent(event)
        }
    }

    fun updateEvent(event: Event){
        viewModelScope.launch (Dispatchers.IO){
            eventRepository.updateEvent(event)
        }
    }

}