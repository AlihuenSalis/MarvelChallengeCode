package com.example.challengecode.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengecode.R
import com.example.challengecode.domain.model.character.Character
import com.example.challengecode.ui.viewHolder.CharacterViewHolder

class CharactersAdapter (
    private val characters: List<Character>,
    private val onCLickListener: (Character) -> Unit)
    : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character_list, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characters[position]
        holder.render(item, onCLickListener)
    }

    override fun getItemCount(): Int = characters.size


}
