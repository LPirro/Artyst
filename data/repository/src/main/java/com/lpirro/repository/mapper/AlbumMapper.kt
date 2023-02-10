package com.lpirro.repository.mapper

import com.lpirro.domain.model.Album
import com.lpirro.network.model.AlbumRemote

interface AlbumMapper {
    fun mapToDomain(albumRemote: AlbumRemote): Album
}
