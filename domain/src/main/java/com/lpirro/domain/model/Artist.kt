package com.lpirro.domain.model

data class Artist(
    val id: String,
    val type: String?,
    val name: String,
    val gender: String?,
    val country: String?,
    val disambiguation: String?,
    val lifeSpan: LifeSpan?,
    val imageUrl: String?,
    val streamingUrls: List<String>?
)
