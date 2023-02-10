package com.lpirro.core.util

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest

object NavigationUtil {

    private fun getArtistDetailUri(artistId: String): Uri {
        return "android-app://com.lpirro.artyst/artist_detail?artistId=$artistId".toUri()
    }

    fun artistDetailDeepLink(artistId: String) = NavDeepLinkRequest.Builder
        .fromUri(getArtistDetailUri(artistId))
        .build()
}
