package com.example.challengecode.data.network

import com.example.challengecode.domain.model.event.EventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventService @Inject constructor(private val api: ComicApiClient) {
    suspend fun getEvents(hash: String): retrofit2.Call<EventResponse> {
        return withContext(Dispatchers.IO) {
            api.getEvents(hash)
        }
    }
}