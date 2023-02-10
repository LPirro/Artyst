package com.lpirro.artist_detail.mapper

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ProfileImageFetcherImplTest {

    private lateinit var profileImageFetcher: ProfileImageFetcher

    @Before
    fun setup() {
        profileImageFetcher = ProfileImageFetcherImpl()
    }

    @Test
    fun `ProfileImageFetcher returns Wikipedia Profile Image Url`() {
        val urlUnderTest = "https://commons.wikimedia.org/wiki/File:Justin_Bieber_ABC.jpg"
        val result = profileImageFetcher.getWikipediaImageFromUrl(urlUnderTest)
        val expected =
            "https://en.wikipedia.org/wiki/en:Special:Filepath/File:Justin_Bieber_ABC.jpg"
        assertEquals(result, expected)
    }

    @Test
    fun `ProfileImageFetcher returns Exception for unsupported urls`() {
        val urlUnderTest = "wrongUrl"
        assertThrows(Exception::class.java) {
            profileImageFetcher.getWikipediaImageFromUrl(urlUnderTest)
        }
    }

    @Test
    fun `ProfileImageFetcher returns Album Cover URL from artistId`() {
        val albumId = "123456789"
        val expected = "https://coverartarchive.org/release-group/$albumId/front"
        val result = profileImageFetcher.getAlbumImageUrl(albumId)
        assertEquals(result, expected)
    }
}
