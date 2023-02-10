package com.lpirro.search.mapper

import com.lpirro.domain.model.Artist
import com.lpirro.search.model.ArtistUi

class ArtistUiMapperImpl : ArtistUiMapper {
    override fun mapToUi(artist: Artist) = ArtistUi(
        id = artist.id,
        name = artist.name,
        type = artist.type ?: "Unknown"
    )
}
