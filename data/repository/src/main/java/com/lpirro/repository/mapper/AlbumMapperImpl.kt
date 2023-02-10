package com.lpirro.repository.mapper

import com.lpirro.domain.model.Album
import com.lpirro.network.model.AlbumRemote

class AlbumMapperImpl : AlbumMapper {
    override fun mapToDomain(albumRemote: AlbumRemote) = Album(
        id = albumRemote.id,
        title = albumRemote.title,
        releaseDate = albumRemote.releaseDate
    )
}
