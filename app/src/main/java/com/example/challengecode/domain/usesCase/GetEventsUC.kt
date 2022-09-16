package com.example.challengecode.domain.usesCase

import com.example.challengecode.data.network.EventService
import com.example.challengecode.domain.model.event.EventResponse
import retrofit2.Call
import javax.inject.Inject

class GetEventsUC @Inject constructor(private val service: EventService) {
    suspend operator fun invoke(hash: String): Call<EventResponse> {
        return service.getEvents(hash)
    }
}