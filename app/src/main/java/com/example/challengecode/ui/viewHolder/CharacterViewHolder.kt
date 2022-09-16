package com.example.challengecode.ui.viewHolder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengecode.R
import com.example.challengecode.databinding.ItemCharacterListBinding
import com.example.challengecode.domain.model.character.Character

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCharacterListBinding.bind(view)

    fun render(character: Character, onClickListener: (Character) -> Unit) {
        Glide.with(binding.imgCharacter.context)
            .load(character.thumbnail.path + ".${character.thumbnail.extension}")
            .error(R.drawable.ic_person)
            .into(binding.imgCharacter)
        binding.txtNameCharacter.text = character.name
        binding.txtDescription.text = character.description
        binding.cvItems.setOnClickListener {
           onClickListener(character)
        }
    }
}