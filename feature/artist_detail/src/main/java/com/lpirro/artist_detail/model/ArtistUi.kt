package com.lpirro.artist_detail.model

data class ArtistUi(
    val name: String,
    val imageUrl: String?,
    val description: String,
    val country: String,
    val gender: String,
    val birthDate: String,
    val streamingServices: StreamingServiceUi
)
