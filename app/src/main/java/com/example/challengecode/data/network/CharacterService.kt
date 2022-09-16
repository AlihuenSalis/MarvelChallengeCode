package com.example.challengecode.data.network

import com.example.challengecode.domain.model.character.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val api: ComicApiClient) {
    suspend fun getCharacterList(hash: String): retrofit2.Call<CharacterResponse> {
        return withContext(Dispatchers.IO) {
            api.getCharacters(hash)
        }
    }
}