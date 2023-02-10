package com.lpirro.search.mapper

import com.lpirro.domain.model.Artist
import com.lpirro.search.model.ArtistUi

interface ArtistUiMapper {
    fun mapToUi(artist: Artist): ArtistUi
}
