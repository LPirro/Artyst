package com.lpirro.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateMargins
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lpirro.core.base.BaseFragment
import com.lpirro.core.extensions.loading
import com.lpirro.core.extensions.textInputAsFlow
import com.lpirro.core.extensions.visible
import com.lpirro.core.util.NavigationUtil
import com.lpirro.search.databinding.FragmentSearchBinding
import com.lpirro.search.presentation.adapter.ArtistAdapter
import com.lpirro.search.viewmodel.SearchViewModel
import com.lpirro.search.viewmodel.SearchViewModel.SearchUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var artistAdapter: ArtistAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        setupRecyclerView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            (binding.toolbar.layoutParams as MarginLayoutParams).updateMargins(top = insets.top)
            windowInsets
        }

        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView.editText.textInputAsFlow()
            .debounce(500)
            .onEach { query -> if (query.isNotEmpty()) viewModel.search(query) }
            .launchIn(lifecycleScope)
    }

    private fun onUiUpdate(uiState: SearchUiState) {
        binding.progressBar.loading = uiState.isLoading
        binding.searchEmptyView.visible = uiState.emptyResult
        binding.searchEmptyView.query = uiState.query ?: ""
        binding.errorView.visible = uiState.searchError
        artistAdapter.submitList(uiState.artists)
    }

    private fun launchArtistDetail(artistId: String) {
        findNavController().navigate(NavigationUtil.artistDetailDeepLink(artistId))
    }

    private fun setupRecyclerView() {
        artistAdapter = ArtistAdapter(::launchArtistDetail)
        binding.artistRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = artistAdapter
            itemAnimator = null
        }
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch { viewModel.uiState.collect(::onUiUpdate) }
            }
        }
    }
}
