package com.lpirro.domain.usecase

import com.lpirro.domain.model.Artist
import com.lpirro.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow

class GetArtistUseCaseImpl(
    private val repository: ArtistRepository,
) : GetArtistUseCase {
    override fun invoke(id: String): Flow<Artist> {
        return repository.getArtist(id)
    }
}
