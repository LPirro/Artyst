package com.lpirro.network.model

import com.google.gson.annotations.SerializedName

data class UrlRemote(
    @SerializedName("type") val type: String,
    @SerializedName("url") val urlResource: UrlResourceRemote
)

data class UrlResourceRemote(@SerializedName("resource") val resourceUrl: String)
