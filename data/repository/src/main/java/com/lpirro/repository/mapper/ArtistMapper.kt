package com.lpirro.repository.mapper

import com.lpirro.domain.model.Artist
import com.lpirro.network.model.ArtistRemote

interface ArtistMapper {
    fun mapToDomain(artistRemote: ArtistRemote): Artist
}
