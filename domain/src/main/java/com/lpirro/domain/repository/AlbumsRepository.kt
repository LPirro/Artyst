package com.lpirro.domain.repository

import com.lpirro.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getAlbums(artistId: String): Flow<List<Album>>
}
