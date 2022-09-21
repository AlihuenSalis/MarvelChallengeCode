package com.example.challengecode.ui.viewHolder

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengecode.R
import com.example.challengecode.databinding.ItemEventListBinding
import com.example.challengecode.domain.model.event.Event
import com.example.challengecode.ui.adapter.CharacterComicsAdapter
import com.example.challengecode.utils.Utils

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEventListBinding.bind(view)
    val utils = Utils()

    fun render(event: Event, position: Int, onClickListener: (Event) -> Unit) {
        Glide.with(binding.eventImage.context)
            .load(event.thumbnail.path + ".${event.thumbnail.extension}")
            .error(R.drawable.ic_person)
            .into(binding.eventImage)
        binding.eventTitle.text = event.title
        binding.eventStartDate.text = utils.setDate(event.start, binding.eventStartDate.context)
        binding.eventEndDate.text = utils.setDate(event.end, binding.eventEndDate.context)

        binding.itemLayout.setOnClickListener {
            val manager = LinearLayoutManager(binding.eventTitle.context)
            binding.comicsRecyclerView.layoutManager = manager
            val itemAdapter = CharacterComicsAdapter(event.comics.items)
            binding.comicsRecyclerView.adapter = itemAdapter
            if (event.showingComics) {
                binding.comicsRecyclerView.isVisible = false
                binding.tvComicsToDebate.isVisible = false
                binding.showingComicsImageView.setImageResource(R.drawable.ic_down_arrow_black)
            } else {
                binding.comicsRecyclerView.isVisible = true
                binding.tvComicsToDebate.isVisible = true
                binding.showingComicsImageView.setImageResource(R.drawable.ic_up_arrow_black)
            }
            onClickListener(event)
        }
    }
}