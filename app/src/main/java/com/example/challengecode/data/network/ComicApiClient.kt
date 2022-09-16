package com.example.challengecode.data.network

import com.example.challengecode.domain.model.character.CharacterResponse
import com.example.challengecode.domain.model.event.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApiClient {

    @GET("characters")
    fun getCharacters(@Query("hash") hash: String): Call<CharacterResponse>

    @GET("events")
    fun getEvents(@Query("hash") hash: String): Call<EventResponse>

}