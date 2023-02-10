package com.lpirro.search.mapper

import com.lpirro.domain.model.Artist
import com.lpirro.domain.model.LifeSpan
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtistUiMapperImplTest {

    private lateinit var artistUiMapper: ArtistUiMapper

    @Before
    fun setup() {
        artistUiMapper = ArtistUiMapperImpl()
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

        val result = artistUiMapper.mapToUi(artist)
        assertEquals(artist.id, result.id)
        assertEquals(artist.name, result.name)
        assertEquals(artist.type, result.type)
    }

    @Test
    fun `Artist with null "type" value returns Artist with Unknown value`() {
        val artist = Artist(
            id = "artistId",
            type = null,
            name = "artistName",
            gender = "male",
            country = "GB",
            disambiguation = "description",
            lifeSpan = LifeSpan("12-01-1954", "18-09-2013"),
            imageUrl = "imageUrl",
            streamingUrls = listOf("spotify", "apple", "tidal")
        )

        val result = artistUiMapper.mapToUi(artist)
        assertEquals(result.id, artist.id)
        assertEquals(result.name, artist.name)
        assertEquals(result.type, "Unknown")
    }
}
