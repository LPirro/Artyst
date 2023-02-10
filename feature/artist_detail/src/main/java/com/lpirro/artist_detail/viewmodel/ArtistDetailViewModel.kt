package com.lpirro.artist_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.artist_detail.mapper.ArtistDetailUiMapper
import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.model.ArtistUi
import com.lpirro.domain.usecase.GetAlbumsUseCase
import com.lpirro.domain.usecase.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val artistUseCase: GetArtistUseCase,
    private val albumsUseCase: GetAlbumsUseCase,
    savedStateHandle: SavedStateHandle,
    private val mapper: ArtistDetailUiMapper
) : ViewModel(), ArtistDetailViewModelContract {

    private val _uiState = MutableStateFlow(ArtistUiState())
    val uiState: StateFlow<ArtistUiState> = _uiState.asStateFlow()

    private var artistId: String

    init {
        artistId = savedStateHandle.get<String>("artistId")!!
        getArtistData(artistId)
    }

    override fun getArtistData(id: String) = viewModelScope.launch {
        _uiState.value = ArtistUiState(isArtistLoading = true)
        try {
            artistUseCase(id).combine(albumsUseCase(id)) { artist, albums ->
                return@combine Pair(artist, albums)
            }.collect {
                _uiState.value = ArtistUiState(
                    artist = mapper.mapToUi(it.first),
                    albums = it.second.map(mapper::mapToUi)
                )
            }
        } catch (e: Exception) {
            _uiState.value = ArtistUiState(error = true, isArtistLoading = false)
            Timber.d(e)
        }
    }

    override fun retry() {
        getArtistData(artistId)
    }

    data class ArtistUiState(
        val isArtistLoading: Boolean = false,
        val artist: ArtistUi? = null,
        val albums: List<AlbumUi> = listOf(),
        val error: Boolean = false
    )
}
