package com.example.challengecode.domain.model.character

data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val comics: Comic
)