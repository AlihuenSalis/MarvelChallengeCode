package com.example.challengecode.ui.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challengecode.databinding.ItemCharacterComicsBinding
import com.example.challengecode.databinding.ItemCharacterListBinding
import com.example.challengecode.domain.model.character.Item

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCharacterComicsBinding.bind(view)

    fun render(item: Item) {
        binding.txtTitleComic.text = item.name
    }
}