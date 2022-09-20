package com.example.challengecode.ui.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengecode.domain.model.character.CharacterResponse
import com.example.challengecode.domain.model.event.Event
import com.example.challengecode.domain.model.event.EventResponse
import com.example.challengecode.domain.usesCase.GetEventsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventsUC: GetEventsUC
) : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val eventList: MutableLiveData<EventResponse> = MutableLiveData()
    val responseError: MutableLiveData<Boolean> = MutableLiveData()
    var eventWithVisibleComics: Event? = null

    fun getEvents(hash: String) {
        viewModelScope.launch {
            val response = getEventsUC(hash)

            response.enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    println("RESPONSE OK= $response")
                    eventList.postValue(response.body())
                    isLoading.postValue(false)
                    
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    println("RESPONSE ERROR = ${t.message}")
                    responseError.postValue(false)
                    isLoading.postValue(false)
                }

            })
        }
    }
}