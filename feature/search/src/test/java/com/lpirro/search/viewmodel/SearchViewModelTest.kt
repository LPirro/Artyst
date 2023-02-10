package com.lpirro.search.viewmodel

import app.cash.turbine.test
import com.lpirro.domain.model.Artist
import com.lpirro.domain.usecase.SearchArtistUseCase
import com.lpirro.search.mapper.ArtistUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val searchArtistUseCase: SearchArtistUseCase = mock()
    private val artistUiMapper: ArtistUiMapper = mock()

    @Before
    fun setup() {
        viewModel = SearchViewModel(searchArtistUseCase, artistUiMapper)
    }

    @Test
    fun `Perform Search Success`() = runTest {
        val mockedArtistList = listOf(
            Artist(
                id = "id",
                type = "Group",
                name = "Pearl Jam",
                gender = null,
                country = "US",
                disambiguation = null,
                lifeSpan = null,
                imageUrl = "imageUrl",
                streamingUrls = null
            )
        )
        whenever(searchArtistUseCase("Pearl Jam")).thenReturn(flow { emit(mockedArtistList) })

        viewModel.search("Pearl Jam")

        viewModel.uiState.test {
            val result = awaitItem()
            assertEquals(result.isLoading, false)
            assertNotNull(result.artists) // assert artists is not null
            assertEquals(result.query, "Pearl Jam")
            assertEquals(result.emptyResult, false)
            assertEquals(result.searchError, false)
        }
    }

    @Test
    fun `Perform Search will return empty result state`() = runTest {
        whenever(searchArtistUseCase("Pearl Jam")).thenReturn(flow { emit(emptyList()) })

        viewModel.search("Pearl Jam")
        viewModel.uiState.test {
            val result = awaitItem()
            assertEquals(result.isLoading, false)
            assertNotNull(result.artists)
            assertEquals(result.query, "Pearl Jam")
            assertEquals(result.emptyResult, true) // assert emptyResult true
            assertEquals(result.searchError, false)
        }
    }

    @Test
    fun `Perform Search Error`() = runTest {
        given(searchArtistUseCase("Pearl Jam")).willAnswer { flow { emit(Exception()) } }
        viewModel.search("Pearl Jam")

        viewModel.uiState.test {
            val result = awaitItem()
            assertEquals(result.isLoading, false)
            assertEquals(result.emptyResult, false)
            assertEquals(result.query, null)
            assertEquals(result.searchError, true) // assert searchError true
        }
    }
}
