package com.itc.recycleviewroomdatabase.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itc.recycleviewroomdatabase.R
import com.itc.recycleviewroomdatabase.adapter.EventAdapter
import com.itc.recycleviewroomdatabase.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Recycler View
        val adapter = EventAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //User View Model
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventViewModel.readAll.observe(viewLifecycleOwner) { event ->

            adapter.setEvents(event.sortedBy {
                it.date
            })
        }

        view.fragment_main_button.setOnClickListener {
            val directionToEntryFragment = MainFragmentDirections.actionMainToEntryFragment()
            findNavController().navigate(directionToEntryFragment)
        }
        return view
    }

}
