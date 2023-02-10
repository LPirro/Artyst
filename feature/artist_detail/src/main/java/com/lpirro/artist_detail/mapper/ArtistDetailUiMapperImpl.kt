package com.lpirro.artist_detail.mapper

import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.model.ArtistUi
import com.lpirro.domain.model.Album
import com.lpirro.domain.model.Artist

class ArtistDetailUiMapperImpl(
    private val profileImageFetcher: ProfileImageFetcher,
    private val streamingServicesMapper: StreamingServicesMapper,
) : ArtistDetailUiMapper {
    override fun mapToUi(artist: Artist) = ArtistUi(
        name = artist.name,
        imageUrl = artist.imageUrl?.let { profileImageFetcher.getWikipediaImageFromUrl(it) },
        description = artist.disambiguation ?: "N/A",
        country = artist.country ?: "N/A",
        gender = artist.gender ?: "N/A",
        birthDate = artist.lifeSpan?.begin ?: "N/A",
        streamingServices = streamingServicesMapper.mapToUi(artist.streamingUrls)
    )

    override fun mapToUi(album: Album) = AlbumUi(
        id = album.id,
        title = album.title,
        releaseDate = album.releaseDate,
        coverUrl = profileImageFetcher.getAlbumImageUrl(album.id)
    )
}
