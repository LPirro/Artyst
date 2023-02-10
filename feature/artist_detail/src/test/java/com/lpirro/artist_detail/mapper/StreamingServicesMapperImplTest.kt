package com.lpirro.artist_detail.mapper

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StreamingServicesMapperImplTest {

    private lateinit var streamingServicesMapper: StreamingServicesMapper

    @Before
    fun setup() {
        streamingServicesMapper = StreamingServicesMapperImpl()
    }

    @Test
    fun `StreamingServicesMapper returns Streaming Services URLs`() {
        val underTest = listOf(
            "http://www.spotify.com/id/1234",
            "http://www.google.com",
            "http://www.randomurl.com/other/paths",
            "http://www.tidal.com/id/22323",
            "http://www.deezer.com/233231",
            "http://www.other.com/32432",
            "http://www.music.apple.com/32432",
            "http://www.amazon.music.com/32432",
        )

        val result = streamingServicesMapper.mapToUi(underTest)
        assertEquals(result.spotifyUrl, "http://www.spotify.com/id/1234")
        assertEquals(result.appleMusicUrl, "http://www.music.apple.com/32432")
        assertEquals(result.amazonMusicUrl, "http://www.amazon.music.com/32432")
        assertEquals(result.tidalUrl, "http://www.tidal.com/id/22323")
        assertEquals(result.deezerUrl, "http://www.deezer.com/233231")
    }
}
