package com.lpirro.network.model

import com.google.gson.annotations.SerializedName

data class ArtistRemote(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String?,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("disambiguation") val disambiguation: String?,
    @SerializedName("life-span") val lifeSpan: LifeSpanRemote?,
    @SerializedName("relations") val urls: List<UrlRemote>?
)
