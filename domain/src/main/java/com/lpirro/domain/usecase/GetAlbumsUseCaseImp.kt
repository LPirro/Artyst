package com.lpirro.domain.usecase

import com.lpirro.domain.model.Album
import com.lpirro.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCaseImp(
    private val repository: AlbumsRepository
) : GetAlbumsUseCase {
    override fun invoke(artistId: String): Flow<List<Album>> {
        return repository.getAlbums(artistId)
    }
}
