package com.example.challengecode.domain.usesCase

import com.example.challengecode.data.network.CharacterService
import com.example.challengecode.domain.model.character.CharacterResponse
import retrofit2.Call
import javax.inject.Inject

class GetCharacterListUC @Inject constructor(private val service: CharacterService) {
    suspend operator fun invoke(hash: String): Call<CharacterResponse> {
        return service.getCharacterList(hash)
    }
}