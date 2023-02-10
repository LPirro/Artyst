package com.lpirro.domain.repository

import com.lpirro.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface SearchArtistRepository {
    fun searchArtist(searchQuery: String): Flow<List<Artist>>
}
