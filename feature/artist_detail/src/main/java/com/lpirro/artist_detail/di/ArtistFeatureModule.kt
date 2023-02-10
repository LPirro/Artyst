package com.lpirro.artist_detail.di

import com.lpirro.artist_detail.mapper.ArtistDetailUiMapper
import com.lpirro.artist_detail.mapper.ArtistDetailUiMapperImpl
import com.lpirro.artist_detail.mapper.ProfileImageFetcher
import com.lpirro.artist_detail.mapper.ProfileImageFetcherImpl
import com.lpirro.artist_detail.mapper.StreamingServicesMapper
import com.lpirro.artist_detail.mapper.StreamingServicesMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ArtistFeatureModule {
    @Provides
    fun provideArtistUiMapper(
        profileImageFetcher: ProfileImageFetcher,
        streamingServicesMapper: StreamingServicesMapper
    ): ArtistDetailUiMapper = ArtistDetailUiMapperImpl(
        profileImageFetcher = profileImageFetcher,
        streamingServicesMapper = streamingServicesMapper
    )

    @Provides
    fun provideProfileImageFetcher(): ProfileImageFetcher = ProfileImageFetcherImpl()

    @Provides
    fun provideStreamingServicesMapper(): StreamingServicesMapper = StreamingServicesMapperImpl()
}
