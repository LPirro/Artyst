package com.lpirro.artist_detail.mapper

import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.model.ArtistUi
import com.lpirro.domain.model.Album
import com.lpirro.domain.model.Artist

interface ArtistDetailUiMapper {
    fun mapToUi(artist: Artist): ArtistUi
    fun mapToUi(album: Album): AlbumUi
}
