package com.example.challengecode.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengecode.databinding.FragmentHomeBinding
import com.example.challengecode.domain.model.character.Character
import com.example.challengecode.ui.adapter.CharactersAdapter
import com.example.challengecode.ui.view.viewModel.HomeViewModel
import com.example.challengecode.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val utils: Utils = Utils()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getCharacters(utils.md5Hash())

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressBarCharacters.isVisible = it
            binding.rvCharacters.isVisible = !it
        }

        homeViewModel.characterList.observe(viewLifecycleOwner) {
            initRecycler(it.data.results)
        }
    }

    private fun initRecycler(characterList: List<Character>) {
        val manager = LinearLayoutManager(requireContext())
        binding.rvCharacters.layoutManager = manager
        val adapter = CharactersAdapter(characterList) {
            showCharacterDetail(it)
        }
        binding.rvCharacters.adapter = adapter

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