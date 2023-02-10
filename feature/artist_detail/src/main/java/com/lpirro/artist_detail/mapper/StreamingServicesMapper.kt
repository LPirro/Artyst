package com.lpirro.artist_detail.mapper

import com.lpirro.artist_detail.model.StreamingServiceUi

interface StreamingServicesMapper {
    fun mapToUi(streamingUrls: List<String>?): StreamingServiceUi
}
