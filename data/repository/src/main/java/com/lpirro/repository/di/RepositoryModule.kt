package com.lpirro.repository.di

import com.lpirro.domain.repository.AlbumsRepository
import com.lpirro.domain.repository.ArtistRepository
import com.lpirro.domain.repository.SearchArtistRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.repository.AlbumRepositoryImpl
import com.lpirro.repository.ArtistRepositoryImpl
import com.lpirro.repository.SearchArtistRepositoryImpl
import com.lpirro.repository.mapper.AlbumMapper
import com.lpirro.repository.mapper.AlbumMapperImpl
import com.lpirro.repository.mapper.ArtistMapper
import com.lpirro.repository.mapper.ArtistMapperImpl
import com.lpirro.repository.mapper.LifeSpanMapper
import com.lpirro.repository.mapper.LifeSpanMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideSearchArtistRepository(
        apiService: MusicBrainzApiService,
        artistMapper: ArtistMapper
    ): SearchArtistRepository {
        return SearchArtistRepositoryImpl(apiService = apiService, artistMapper = artistMapper)
    }

    @Provides
    fun provideArtistRepository(
        apiService: MusicBrainzApiService,
        artistMapper: ArtistMapper
    ): ArtistRepository {
        return ArtistRepositoryImpl(apiService = apiService, artistMapper = artistMapper)
    }

    @Provides
    fun provideAlbumRepository(
        apiService: MusicBrainzApiService,
        albumMapper: AlbumMapper
    ): AlbumsRepository {
        return AlbumRepositoryImpl(apiService = apiService, albumMapper = albumMapper)
    }

    @Provides
    fun provideArtistMapper(
        lifeSpanMapper: LifeSpanMapper
    ): ArtistMapper {
        return ArtistMapperImpl(lifeSpanMapper = lifeSpanMapper)
    }

    @Provides
    fun provideLifeSpanMapper(): LifeSpanMapper = LifeSpanMapperImpl()

    @Provides
    fun provideAlbumMapper(): AlbumMapper = AlbumMapperImpl()
}
