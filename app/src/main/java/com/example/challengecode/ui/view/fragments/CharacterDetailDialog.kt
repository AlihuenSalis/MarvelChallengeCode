package com.example.challengecode.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.challengecode.R
import com.example.challengecode.databinding.DialogCharacterDetailBinding
import com.example.challengecode.domain.model.character.Character
import com.example.challengecode.ui.adapter.CharacterComicsAdapter
import com.example.challengecode.ui.view.viewModel.CharacterDetailViewModel

class CharacterDetailDialog(private val character: Character) : DialogFragment() {

    private var _binding: DialogCharacterDetailBinding? = null
    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        characterDetailViewModel =
            ViewModelProvider(this)[CharacterDetailViewModel::class.java]

        _binding = DialogCharacterDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        binding.characterDetailToolbar.setNavigationOnClickListener { dismiss() }

    }

    private fun initUi() {
        binding.characterDetailToolbar.title = character.name
        if (character.description.isEmpty()) {
            binding.txtDescription.isVisible = false
        } else {binding.txtDescription.text = character.description}

        Glide.with(requireContext())
            .load(character.thumbnail.path+".${character.thumbnail.extension}")
            .error(R.drawable.ic_person)
            .into(binding.imgCharacter)

        initRecycler()
    }

    private fun initRecycler() {
        val manager = LinearLayoutManager(requireContext())
        binding.rvCharacterComics.layoutManager = manager
        val adapter = CharacterComicsAdapter(character.comics.items)
        binding.rvCharacterComics.adapter = adapter
    }
}