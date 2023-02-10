package com.lpirro.artist_detail.mapper

import java.util.regex.Pattern

private const val FILE_REGEX = "File:.*"
private const val WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/wiki/en:Special:Filepath/"
private const val COVER_ART_BASE_URL = "https://coverartarchive.org/release-group/"

class ProfileImageFetcherImpl : ProfileImageFetcher {
    override fun getWikipediaImageFromUrl(url: String): String {
        val pattern = Pattern.compile(FILE_REGEX, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(url)
        if (matcher.find()) {
            return buildString {
                append(WIKIPEDIA_BASE_URL)
                append(matcher.group())
            }
        }
        throw Exception("Not being able to parse $url")
    }

    override fun getAlbumImageUrl(albumId: String): String {
        return buildString {
            append(COVER_ART_BASE_URL)
            append(albumId)
            append("/front")
        }
    }
}
