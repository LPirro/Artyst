package com.lpirro.domain.usecase

import com.lpirro.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface GetArtistUseCase {
    operator fun invoke(id: String): Flow<Artist>
}
