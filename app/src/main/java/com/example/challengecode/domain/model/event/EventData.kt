package com.example.challengecode.domain.model.event

import com.example.challengecode.domain.model.character.Character

data class EventData (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Event>
        )