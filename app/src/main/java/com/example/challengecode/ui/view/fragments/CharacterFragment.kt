package com.example.challengecode.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengecode.R
import com.example.challengecode.databinding.FragmentCharacterBinding
import com.example.challengecode.domain.model.character.Character
import com.example.challengecode.ui.adapter.CharactersAdapter
import com.example.challengecode.ui.adapter.EventsAdapter
import com.example.challengecode.ui.view.viewModel.CharacterViewModel
import com.example.challengecode.utils.MarginItemDecoration
import com.example.challengecode.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private lateinit var characterViewModel: CharacterViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val utils: Utils = Utils()
    private lateinit var characterAdapter: CharactersAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        characterViewModel =
            ViewModelProvider(this)[CharacterViewModel::class.java]

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        characterViewModel.getCharacters(utils.md5Hash())

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressBarCharacters.isVisible = it
            binding.rvCharacters.isVisible = !it
        }

        characterViewModel.characterList.observe(viewLifecycleOwner) {
            initRecycler(it.data.results)
        }
    }

    private fun initRecycler(characterList: List<Character>) {
        val manager = LinearLayoutManager(requireContext())
//        val decoration = DividerItemDecoration(requireContext(), manager.orientation)
        binding.rvCharacters.layoutManager = manager
        characterAdapter = CharactersAdapter(characterList) {
            showCharacterDetail(it)
        }
        binding.rvCharacters.adapter = characterAdapter
        binding.rvCharacters.addItemDecoration(
            MarginItemDecoration(resources.getDimension(R.dimen.item_decoration).toInt())
        )

    }

    private fun showCharacterDetail(character: Character) {
//        val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//        // Create and show the dialog.
//        // Create and show the dialog.
//        val newFragment = CharacterDetailFragment()
//        newFragment.show(ft, "dialog")


        val fragment: DialogFragment = CharacterDetailDialog(character)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragment.show(fragmentTransaction, "dialog")
//        fragmentTransaction.replace(R.id.content, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}