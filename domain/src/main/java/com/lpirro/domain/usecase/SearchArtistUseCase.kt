package com.lpirro.domain.usecase

import com.lpirro.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface SearchArtistUseCase {
    operator fun invoke(searchQuery: String): Flow<List<Artist>>
}
