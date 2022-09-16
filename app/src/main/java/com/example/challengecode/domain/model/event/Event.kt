package com.example.challengecode.domain.model.event

import com.example.challengecode.domain.model.character.Thumbnail

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val resourceURI: String,
    val urls: List<Url>,
    val modified: String,
    val start: String,
    val end: String,
    val thumbnail: Thumbnail
)