package com.itc.recycleviewroomdatabase.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itc.recycleviewroomdatabase.R
import com.itc.recycleviewroomdatabase.model.Event
import com.itc.recycleviewroomdatabase.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_entry.*
import kotlinx.android.synthetic.main.fragment_entry.view.*
import java.util.*

class EntryFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        var selectedDate = 0L
        view.calender_event.setOnDateChangeListener { _, year, month, day ->
            //show the selected date as a toast
            val c: Calendar = Calendar.getInstance()
            c.set(year, month, day)
            selectedDate = c.timeInMillis //this is what you want to use later
        }

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        view.save_button.setOnClickListener {
            insertData(selectedDate)
        }

        return view
    }

    private fun insertData(date: Long) {
        val name = edit_text_event_name.text.toString()
        val category = edit_text_event_category.text.toString()
        val date = date
        if (inputCheck(name, category)) {
            val event = Event(0, name, category, date)
            mEventViewModel.addEvent(event)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_entry_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(name: String, category: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(category))
    }
}


