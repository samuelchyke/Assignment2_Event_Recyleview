package com.itc.recycleviewroomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.itc.recycleviewroomdatabase.util.DateUtils.dateToString
import com.itc.recycleviewroomdatabase.util.DateUtils.longToDate
import com.itc.recycleviewroomdatabase.R
import com.itc.recycleviewroomdatabase.model.Event
import com.itc.recycleviewroomdatabase.ui.fragments.MainFragmentDirections
import kotlinx.android.synthetic.main.recycleview_card.view.*
import java.util.ArrayList

class EventAdapter : RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    private var mEventList = emptyList<Event>()

    fun setEvents(events: List<Event>) {
        this.mEventList = events
        notifyDataSetChanged()
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycleview_card, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentEvent = mEventList[position]
        holder.itemView.event_name.text = currentEvent.name
        holder.itemView.event_category.text = currentEvent.category
        holder.itemView.event_date.text = dateToString(longToDate(currentEvent.date))

        holder.itemView.recyclerViewCard.setOnClickListener {
            val directionToMainFragment = MainFragmentDirections.actionMainToDetailFragment(currentEvent)
            holder.itemView.findNavController().navigate(directionToMainFragment)
            }
    }

    override fun getItemCount(): Int = mEventList.size

}
