package com.lpirro.domain.di

import com.lpirro.domain.repository.AlbumsRepository
import com.lpirro.domain.repository.ArtistRepository
import com.lpirro.domain.repository.SearchArtistRepository
import com.lpirro.domain.usecase.GetAlbumsUseCase
import com.lpirro.domain.usecase.GetAlbumsUseCaseImp
import com.lpirro.domain.usecase.GetArtistUseCase
import com.lpirro.domain.usecase.GetArtistUseCaseImpl
import com.lpirro.domain.usecase.SearchArtistUseCase
import com.lpirro.domain.usecase.SearchArtistUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideSearchArtistUseCase(repository: SearchArtistRepository): SearchArtistUseCase {
        return SearchArtistUseCaseImpl(repository)
    }

    @Provides
    fun provideGetArtistUseCase(repository: ArtistRepository): GetArtistUseCase {
        return GetArtistUseCaseImpl(repository)
    }

    @Provides
    fun provideGetAlbumsUseCase(repository: AlbumsRepository): GetAlbumsUseCase {
        return GetAlbumsUseCaseImp(repository)
    }
}
