package com.lpirro.network.model

import com.google.gson.annotations.SerializedName

data class ReleaseGroupsRemote(@SerializedName("release-groups") val albums: List<AlbumRemote>)

data class AlbumRemote(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("first-release-date") val releaseDate: String
)
