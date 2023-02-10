package com.lpirro.artist_detail.mapper

interface ProfileImageFetcher {
    fun getWikipediaImageFromUrl(url: String): String
    fun getAlbumImageUrl(albumId: String): String
}
