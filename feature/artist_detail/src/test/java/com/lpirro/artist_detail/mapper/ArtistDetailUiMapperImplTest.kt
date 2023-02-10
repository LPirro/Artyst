package com.lpirro.artist_detail.mapper

import com.lpirro.artist_detail.model.StreamingServiceUi
import com.lpirro.domain.model.Album
import com.lpirro.domain.model.Artist
import com.lpirro.domain.model.LifeSpan
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ArtistDetailUiMapperImplTest {

    private lateinit var artistDetailUiMapper: ArtistDetailUiMapper
    private val profileImageFetcher: ProfileImageFetcher = mock()
    private val streamingServicesMapper: StreamingServicesMapper = mock()

    @Before
    fun setup() {
        artistDetailUiMapper =
            ArtistDetailUiMapperImpl(profileImageFetcher, streamingServicesMapper)
        whenever(profileImageFetcher.getAlbumImageUrl(any())).thenReturn("albumUrl")
        whenever(profileImageFetcher.getWikipediaImageFromUrl(any())).thenReturn("imageUrl")
        whenever(streamingServicesMapper.mapToUi(any())).thenReturn(StreamingServiceUi())
    }

    @Test
    fun `Artist to ArtistUi Maps Correctly`() {
        val artist = Artist(
            id = "artistId",
            type = "Person",
            name = "artistName",
            gender = "male",
            country = "GB",
            disambiguation = "description",
            lifeSpan = LifeSpan("12-01-1954", "18-09-2013"),
            imageUrl = "imageUrl",
            streamingUrls = listOf("spotify", "apple", "tidal")
        )

        val result = artistDetailUiMapper.mapToUi(artist)
        assertEquals(result.name, artist.name)
        assertEquals(result.imageUrl, artist.imageUrl)
        assertEquals(result.description, artist.disambiguation)
        assertEquals(result.country, artist.country)
        assertEquals(result.gender, artist.gender)
        assertEquals(result.birthDate, artist.lifeSpan!!.begin)
    }

    @Test
    fun `Artist to ArtistUi null values returns Not Available String`() {
        val artist = Artist(
            id = "artistId",
            type = "Person",
            name = "artistName",
            gender = null,
            country = null,
            disambiguation = null,
            lifeSpan = LifeSpan(null, "18-09-2013"),
            imageUrl = "imageUrl",
            streamingUrls = listOf("spotify", "apple", "tidal")
        )

        val result = artistDetailUiMapper.mapToUi(artist)
        assertEquals(result.name, artist.name)
        assertEquals(result.imageUrl, artist.imageUrl)
        assertEquals(result.description, "N/A")
        assertEquals(result.country, "N/A")
        assertEquals(result.gender, "N/A")
        assertEquals(result.birthDate, "N/A")
    }

    @Test
    fun `Album to AlbumUi Maps Correctly`() {
        val album = Album(
            id = "albumId",
            title = "albumTitle",
            releaseDate = "24-07-1993"
        )

        val result = artistDetailUiMapper.mapToUi(album)
        assertEquals(result.id, album.id)
        assertEquals(result.title, album.title)
        assertEquals(result.releaseDate, album.releaseDate)
    }
}
