package com.itc.recycleviewroomdatabase.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itc.recycleviewroomdatabase.R
import com.itc.recycleviewroomdatabase.model.Event
import com.itc.recycleviewroomdatabase.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.fragment_entry.view.*
import java.util.*

class UpdateFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        var selectedDate = 0L
        view.update_calender_event.setOnDateChangeListener { _, year, month, day ->
            //show the selected date as a toast
            val c: Calendar = Calendar.getInstance()
            c.set(year, month, day)
            selectedDate = c.timeInMillis //this is what you want to use later
        }

        view.update_event_name.setText(args.currentEvent.name)
        view.update_event_category.setText(args.currentEvent.category)
        view.update_calender_event.date = args.currentEvent.date

        view.update_button.setOnClickListener {
            updateData(selectedDate)
        }


        return view
    }

    private fun updateData(date: Long) {
        val name = update_event_name.text.toString()
        val category = update_event_category.text.toString()
        val date = date

        if (inputCheck(name, category)) {
            //Create updated user event
            val updatedEvent = Event(args.currentEvent.id, name, category, date)
            //Update current user
            mEventViewModel.updateEvent(updatedEvent)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_detail_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(name: String, category: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(category))
    }



}