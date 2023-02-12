package com.lpirro.repository

import app.cash.turbine.test
import com.lpirro.domain.model.Artist
import com.lpirro.domain.model.LifeSpan
import com.lpirro.domain.repository.ArtistRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.network.model.ArtistRemote
import com.lpirro.network.model.LifeSpanRemote
import com.lpirro.repository.mapper.ArtistMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ArtistRepositoryImplTest {

    private lateinit var artistRepository: ArtistRepository
    private val apiService: MusicBrainzApiService = mock()
    private val artistMapper: ArtistMapper = mock()

    @Before
    fun setup() {
        artistRepository = ArtistRepositoryImpl(apiService, artistMapper)
    }

    @Test
    fun `When getArtist() is called returns the correct Artist`() = runTest {
        val artistId = "artistId"
        whenever(apiService.getArtist(artistId)).thenReturn(mockedArtistRemote())
        whenever(artistMapper.mapToDomain(any())).thenReturn(mockedArtist())

        artistRepository.getArtist(artistId).test {
            val result = awaitItem()
            Assert.assertNotNull(result)
            Assert.assertEquals(result.id, "artistId")
            awaitComplete()
        }
    }

    private fun mockedArtistRemote() = ArtistRemote(
        id = "artistId",
        type = "Person",
        name = "artistName",
        gender = "male",
        country = "GB",
        disambiguation = "description",
        lifeSpan = LifeSpanRemote("12-01-1954", "18-09-2013"),
        urls = emptyList()
    )

    private fun mockedArtist() = Artist(
        id = "artistId",
        type = "Person",
        name = "artistName",
        gender = "male",
        country = "GB",
        disambiguation = "description",
        lifeSpan = LifeSpan("12-01-1954", "18-09-2013"),
        imageUrl = "imageUrl",
        streamingUrls = emptyList()
    )
}
