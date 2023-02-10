package com.lpirro.artist_detail.mapper

import com.lpirro.artist_detail.model.StreamingServiceUi

class StreamingServicesMapperImpl : StreamingServicesMapper {
    override fun mapToUi(streamingUrls: List<String>?) = StreamingServiceUi(
        spotifyUrl = streamingUrls?.firstOrNull { it.contains("spotify", false) },
        appleMusicUrl = streamingUrls?.firstOrNull { it.contains("apple", false) },
        amazonMusicUrl = streamingUrls?.firstOrNull { it.contains("amazon", false) },
        tidalUrl = streamingUrls?.firstOrNull { it.contains("tidal", false) },
        deezerUrl = streamingUrls?.firstOrNull { it.contains("deezer", false) },
    )
}
