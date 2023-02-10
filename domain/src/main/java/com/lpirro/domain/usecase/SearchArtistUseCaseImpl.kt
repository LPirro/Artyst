package com.lpirro.domain.usecase

import com.lpirro.domain.model.Artist
import com.lpirro.domain.repository.SearchArtistRepository
import kotlinx.coroutines.flow.Flow

class SearchArtistUseCaseImpl(
    private val repository: SearchArtistRepository
) : SearchArtistUseCase {
    override fun invoke(searchQuery: String): Flow<List<Artist>> {
        return repository.searchArtist(searchQuery)
    }
}
