package com.lpirro.repository.mapper

import com.lpirro.network.model.AlbumRemote
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AlbumMapperImplTest {

    lateinit var albumMapper: AlbumMapper

    @Before
    fun setup() {
        albumMapper = AlbumMapperImpl()
    }

    @Test
    fun `AlbumRemote to Album Maps Correctly`() {
        val albumRemote = AlbumRemote("albumId", "albumTitle", "12-12-2020")
        val result = albumMapper.mapToDomain(albumRemote)

        assertEquals(albumRemote.id, result.id)
        assertEquals(albumRemote.title, result.title)
        assertEquals(albumRemote.releaseDate, result.releaseDate)
    }
}
