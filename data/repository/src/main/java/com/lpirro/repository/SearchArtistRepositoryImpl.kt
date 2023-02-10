package com.lpirro.repository

import com.lpirro.domain.repository.SearchArtistRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.repository.mapper.ArtistMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchArtistRepositoryImpl(
    private val apiService: MusicBrainzApiService,
    private val artistMapper: ArtistMapper
) : SearchArtistRepository {
    override fun searchArtist(searchQuery: String) = flow {
        val result = apiService.searchArtists(searchQuery, 10, 0)
        emit(result.artists.map(artistMapper::mapToDomain))
    }.flowOn(Dispatchers.IO)
}
