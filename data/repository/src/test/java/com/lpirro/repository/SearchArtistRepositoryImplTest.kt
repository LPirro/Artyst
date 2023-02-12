package com.lpirro.repository

import app.cash.turbine.test
import com.lpirro.domain.model.Artist
import com.lpirro.domain.model.LifeSpan
import com.lpirro.domain.repository.SearchArtistRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.network.model.ArtistRemote
import com.lpirro.network.model.LifeSpanRemote
import com.lpirro.network.model.PaginatedResultRemote
import com.lpirro.repository.mapper.ArtistMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class SearchArtistRepositoryImplTest {

    private lateinit var searchArtistRepository: SearchArtistRepository
    private val apiService: MusicBrainzApiService = mock()
    private val artistMapper: ArtistMapper = mock()

    @Before
    fun setup() {
        searchArtistRepository = SearchArtistRepositoryImpl(apiService, artistMapper)
    }

    @Test
    fun `When searchArtist() is called returns the search result`() = runTest {
        val query = "searchQuery"
        whenever(
            apiService.searchArtists(
                query,
                10,
                0
            )
        ).thenReturn(mockedArtistListRemote())
        whenever(artistMapper.mapToDomain(any())).thenReturn(mockedArtist())

        searchArtistRepository.searchArtist(query).test {
            val result = awaitItem()
            assertNotNull(result)
            assertEquals(result.size, 2)
            awaitComplete()
        }
    }

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

    private fun mockedArtistListRemote(): PaginatedResultRemote<List<ArtistRemote>> {
        val artistList = listOf(
            ArtistRemote(
                id = "artistId",
                type = "Person",
                name = "artistName",
                gender = "male",
                country = "GB",
                disambiguation = "description",
                lifeSpan = LifeSpanRemote("12-01-1954", "18-09-2013"),
                urls = emptyList()
            ),
            ArtistRemote(
                id = "artistId",
                type = "Person",
                name = "artistName",
                gender = "male",
                country = "GB",
                disambiguation = "description",
                lifeSpan = LifeSpanRemote("12-01-1954", "18-09-2013"),
                urls = emptyList()
            )
        )
        return PaginatedResultRemote(0, artistList)
    }
}
