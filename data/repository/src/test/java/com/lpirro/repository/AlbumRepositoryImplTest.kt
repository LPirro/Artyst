package com.lpirro.repository

import app.cash.turbine.test
import com.lpirro.domain.model.Album
import com.lpirro.domain.repository.AlbumsRepository
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.network.model.AlbumRemote
import com.lpirro.network.model.ReleaseGroupsRemote
import com.lpirro.repository.mapper.AlbumMapper
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
class AlbumRepositoryImplTest {

    private lateinit var albumsRepository: AlbumsRepository
    private val apiService: MusicBrainzApiService = mock()
    private val albumMapper: AlbumMapper = mock()

    @Before
    fun setup() {
        albumsRepository = AlbumRepositoryImpl(apiService, albumMapper)
    }

    @Test
    fun `When getAlbums() is called returns the correct Album list of the Artist`() = runTest {
        val artistId = "artistId"
        whenever(apiService.releasedAlbums(artistId)).thenReturn(mockedAlbumsRemote())
        whenever(albumMapper.mapToDomain(any())).thenReturn(mockedAlbum())

        albumsRepository.getAlbums(artistId).test {
            val result = awaitItem()
            assertNotNull(result)
            assertEquals(result.size, 1)
            assertEquals(result.first().id, "albumId")
            awaitComplete()
        }
    }

    private fun mockedAlbumsRemote(): ReleaseGroupsRemote {
        val albums = listOf(AlbumRemote("albumId", "Title 1", "10-10-2000"))
        return ReleaseGroupsRemote(albums)
    }

    private fun mockedAlbum(): Album {
        return Album("albumId", "Title 1", "10-10-2000")
    }
}
