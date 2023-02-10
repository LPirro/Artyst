package com.lpirro.repository

import com.lpirro.domain.repository.ArtistRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.repository.mapper.ArtistMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtistRepositoryImpl(
    private val apiService: MusicBrainzApiService,
    private val artistMapper: ArtistMapper
) : ArtistRepository {
    override fun getArtist(id: String) = flow {
        val result = apiService.getArtist(id)
        emit(artistMapper.mapToDomain(result))
    }.flowOn(Dispatchers.IO)
}
