package com.lpirro.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.domain.usecase.SearchArtistUseCase
import com.lpirro.search.mapper.ArtistUiMapper
import com.lpirro.search.model.ArtistUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase,
    private val artistMapper: ArtistUiMapper
) : ViewModel(), SearchViewModelContract {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private lateinit var searchQuery: String

    override fun search(searchQuery: String): Job {
        this.searchQuery = searchQuery
        _uiState.update { it.copy(isLoading = true) }
        return searchArtistUseCase(searchQuery)
            .onEach { artists ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        artists = artists.map(artistMapper::mapToUi),
                        query = searchQuery,
                        emptyResult = artists.isEmpty(),
                        searchError = false
                    )
                }
            }
            .catch { _uiState.value = SearchUiState(searchError = true, isLoading = false) }
            .launchIn(viewModelScope)
    }

    data class SearchUiState(
        val isLoading: Boolean = false,
        val artists: List<ArtistUi> = listOf(),
        val emptyResult: Boolean = false,
        val query: String? = null,
        val searchError: Boolean = false
    )
}
