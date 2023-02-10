package com.lpirro.domain.repository

import com.lpirro.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    fun getArtist(id: String): Flow<Artist>
}
