package com.lpirro.network.model

import com.google.gson.annotations.SerializedName

data class LifeSpanRemote(
    @SerializedName("begin") val begin: String?,
    @SerializedName("end") val end: String?
)
