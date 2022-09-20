package com.example.challengecode.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengecode.R
import com.example.challengecode.databinding.ItemEventListBinding
import com.example.challengecode.domain.model.event.Event
import com.example.challengecode.ui.viewHolder.EventViewHolder

class EventsAdapter (private val events: List<Event>,
                     private val onCLickListener: (Event) -> Unit)
    : RecyclerView.Adapter<EventViewHolder>(){

    var postFillRecycler: ((Event) -> Unit)? = null
    private val _list = mutableListOf<Event>()
    val list get() = _list as List<Event>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(layoutInflater.inflate(R.layout.item_event_list, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = events[position]
        holder.render(item, position, onCLickListener)
    }

    override fun getItemCount(): Int = events.size

    fun updatedItems(items: Collection<Event>) {
        list.forEachIndexed { i, element ->
            items.forEach { item ->
                if (element == item)
                    notifyItemChanged(i)
            }
        }
    }

}