package com.lpirro.repository

import com.lpirro.domain.repository.AlbumsRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.repository.mapper.AlbumMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AlbumRepositoryImpl(
    private val apiService: MusicBrainzApiService,
    private val albumMapper: AlbumMapper
) : AlbumsRepository {
    override fun getAlbums(artistId: String) = flow {
        val result = apiService.releasedAlbums(artistId).albums
        emit(result.map(albumMapper::mapToDomain))
    }.flowOn(Dispatchers.IO)
}
