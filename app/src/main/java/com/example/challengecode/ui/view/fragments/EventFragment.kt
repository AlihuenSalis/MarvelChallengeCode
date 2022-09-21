package com.example.challengecode.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengecode.R
import com.example.challengecode.databinding.FragmentEventBinding
import com.example.challengecode.domain.model.character.Comic
import com.example.challengecode.domain.model.event.Event
import com.example.challengecode.ui.adapter.CharactersAdapter
import com.example.challengecode.ui.adapter.EventsAdapter
import com.example.challengecode.ui.view.viewModel.EventViewModel
import com.example.challengecode.utils.MarginItemDecoration
import com.example.challengecode.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    private var _binding: FragmentEventBinding? = null
    private lateinit var eventViewModel: EventViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var eventAdapter: EventsAdapter

    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventViewModel =
            ViewModelProvider(this)[EventViewModel::class.java]

        _binding = FragmentEventBinding.inflate(inflater, container, false)

        eventViewModel.getEvents(utils.md5Hash())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventViewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressBarEvents.isVisible = it
            binding.rvEvents.isVisible = !it
        }

        eventViewModel.eventList.observe(viewLifecycleOwner) {
            initRecycler(it.data.results.sortedBy {
                it.start
            })
        }

    }

    private fun initRecycler(eventList: List<Event>) {
        val manager = LinearLayoutManager(requireContext())
        binding.rvEvents.layoutManager = manager
        eventAdapter = EventsAdapter(eventList) {eventClicked ->
            eventAdapter.updatedItems(changeExpandedEvent(eventClicked))
        }
        binding.rvEvents.adapter = eventAdapter
        binding.rvEvents.addItemDecoration(
            MarginItemDecoration(resources.getDimension(R.dimen.item_decoration).toInt())
        )
    }

    private fun changeExpandedEvent(event: Event): List<Event>  {
        val events = arrayListOf(event)
        eventViewModel.eventWithVisibleComics?.let {
            events.add(it)
        }
        if (!event.showingComics) {

            eventViewModel.eventWithVisibleComics?.showingComics = false
            eventViewModel.eventWithVisibleComics = event
        } else {
            eventViewModel.eventWithVisibleComics = null
        }
        event.showingComics = !event.showingComics
        return events



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}