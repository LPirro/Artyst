package com.lpirro.search.di

import com.lpirro.search.mapper.ArtistUiMapper
import com.lpirro.search.mapper.ArtistUiMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SearchModule {
    @Provides
    fun provideArtistMapper(): ArtistUiMapper = ArtistUiMapperImpl()
}
