package com.lpirro.domain.usecase

import com.lpirro.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    operator fun invoke(artistId: String): Flow<List<Album>>
}
