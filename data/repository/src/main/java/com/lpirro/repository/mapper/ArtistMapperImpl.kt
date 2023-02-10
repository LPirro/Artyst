package com.lpirro.repository.mapper

import com.lpirro.domain.model.Artist
import com.lpirro.network.model.ArtistRemote

class ArtistMapperImpl(
    private val lifeSpanMapper: LifeSpanMapper
) : ArtistMapper {
    override fun mapToDomain(artistRemote: ArtistRemote) = Artist(
        id = artistRemote.id,
        type = artistRemote.type,
        name = artistRemote.name,
        gender = artistRemote.gender,
        country = artistRemote.country,
        disambiguation = artistRemote.disambiguation,
        lifeSpan = artistRemote.lifeSpan?.let { lifeSpanMapper.mapToDomain(it) },
        streamingUrls = artistRemote.urls?.filter { it.type.contains("streaming") }?.map { it.urlResource.resourceUrl },
        imageUrl = artistRemote.urls?.filter { it.type == "image" }?.map { it.urlResource.resourceUrl }?.firstOrNull()
    )
}
