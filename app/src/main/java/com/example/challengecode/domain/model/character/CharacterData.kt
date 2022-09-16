package com.example.challengecode.domain.model.character

import com.example.challengecode.domain.model.character.Character

data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)