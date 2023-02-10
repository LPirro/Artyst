package com.lpirro.artist_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.lpirro.artist_detail.mapper.ArtistDetailUiMapper
import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.model.ArtistUi
import com.lpirro.artist_detail.model.StreamingServiceUi
import com.lpirro.domain.model.Album
import com.lpirro.domain.model.Artist
import com.lpirro.domain.usecase.GetAlbumsUseCase
import com.lpirro.domain.usecase.GetArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
class ArtistDetailViewModelTest {

    private lateinit var viewModel: ArtistDetailViewModel
    private lateinit var savedStateHandle: SavedStateHandle
    private val getArtistUseCase: GetArtistUseCase = mock()
    private val getAlbumsUseCase: GetAlbumsUseCase = mock()
    private val artistDetailUiMapper: ArtistDetailUiMapper = mock()

    private val mockedArtist = Artist(
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
    private val mockedArtistUi = ArtistUi(
        name = "Pearl Jam",
        gender = "Male",
        country = "US",
        description = "description",
        birthDate = "1993",
        imageUrl = "imageUrl",
        streamingServices = StreamingServiceUi(),
    )

    private val mockedAlbums = listOf(Album(id = "albumId", title = "Title", releaseDate = "22-03-1999"))
    private val mockedAlbumUi = AlbumUi(title = "title", releaseDate = "releaseDate", id = "id", coverUrl = null)

    @Before
    fun setup() {
        savedStateHandle = SavedStateHandle().apply {
            set("artistId", "artistId")
        }

        viewModel = ArtistDetailViewModel(
            getArtistUseCase,
            getAlbumsUseCase,
            savedStateHandle,
            artistDetailUiMapper
        )
    }

    @Test
    fun `Get Artist Data Success`() = runTest {
        whenever(getArtistUseCase("artistId")).thenReturn(flow { emit(mockedArtist) })
        whenever(getAlbumsUseCase("artistId")).thenReturn(flow { emit(mockedAlbums) })

        whenever(artistDetailUiMapper.mapToUi(mockedArtist)).thenReturn(mockedArtistUi)
        whenever(artistDetailUiMapper.mapToUi(any<Album>())).thenReturn(mockedAlbumUi)

        viewModel.getArtistData("artistId")
        viewModel.uiState.test {
            val result = awaitItem()
            assertEquals(result.isArtistLoading, false)
            assertNotNull(result.artist) // assert artist is not null
            assertNotNull(result.albums) // assert albums is not null
            assertEquals(result.error, false)
        }
    }
}
