package com.lpirro.repository.mapper

import com.lpirro.domain.model.LifeSpan
import com.lpirro.network.model.ArtistRemote
import com.lpirro.network.model.LifeSpanRemote
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ArtistMapperImplTest {

    private lateinit var artistMapper: ArtistMapper
    private val lifeSpanMapper: LifeSpanMapper = mock()

    @Before
    fun setup() {
        artistMapper = ArtistMapperImpl(lifeSpanMapper)
        whenever(lifeSpanMapper.mapToDomain(any())).thenReturn(LifeSpan("12-01-1954", "18-09-2013"))
    }

    @Test
    fun `ArtistRemote to Artist Maps Correctly`() {
        val artistRemote = ArtistRemote(
            id = "artistId",
            type = "artistType",
            name = "artistName",
            gender = "male",
            country = "GB",
            disambiguation = "description",
            lifeSpan = LifeSpanRemote("12-01-1954", "18-09-2013"),
            urls = emptyList()
        )

        val result = artistMapper.mapToDomain(artistRemote)

        assertEquals(artistRemote.id, result.id)
        assertEquals(artistRemote.type, result.type)
        assertEquals(artistRemote.name, result.name)
        assertEquals(artistRemote.gender, result.gender)
        assertEquals(artistRemote.country, result.country)
        assertEquals(artistRemote.disambiguation, result.disambiguation)
        assertLifeSpan(artistRemote.lifeSpan!!, result.lifeSpan!!)
        assertEquals(artistRemote.urls, result.streamingUrls)
    }

    private fun assertLifeSpan(lifeSpanRemote: LifeSpanRemote, lifeSpan: LifeSpan) {
        assertEquals(lifeSpanRemote.begin, lifeSpan.begin)
        assertEquals(lifeSpanRemote.end, lifeSpan.end)
    }
}
