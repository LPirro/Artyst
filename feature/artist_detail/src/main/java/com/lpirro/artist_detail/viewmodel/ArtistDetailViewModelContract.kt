package com.lpirro.artist_detail.viewmodel

import kotlinx.coroutines.Job

interface ArtistDetailViewModelContract {
    fun getArtistData(id: String): Job
    fun retry()
}
