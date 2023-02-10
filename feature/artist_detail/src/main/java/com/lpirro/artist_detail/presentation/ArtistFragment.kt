package com.lpirro.artist_detail.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.lpirro.artist_detail.R
import com.lpirro.artist_detail.databinding.FragmentArtistBinding
import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.model.ArtistUi
import com.lpirro.artist_detail.viewmodel.ArtistDetailViewModel
import com.lpirro.core.base.BaseFragment
import com.lpirro.core.extensions.setCustomAttribute
import com.lpirro.core.extensions.startActionViewIntent
import com.lpirro.core.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistFragment : BaseFragment<FragmentArtistBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArtistBinding
        get() = FragmentArtistBinding::inflate

    private val viewModel: ArtistDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    private fun onUiUpdate(uiState: ArtistDetailViewModel.ArtistUiState) {
        binding.progressBar.visible = uiState.isArtistLoading
        binding.artistContent.visible = !uiState.isArtistLoading
        binding.errorView.visible = uiState.error
        binding.errorView.retryClickListener = viewModel::retry
        uiState.artist?.let { setData(it, uiState.albums) }
    }

    private fun setData(artist: ArtistUi, albums: List<AlbumUi>) {
        binding.artistName.text = artist.name
        binding.artistDescription.text = artist.description
        binding.card.country = artist.country
        binding.card.gender = artist.gender
        binding.card.birthDate = artist.birthDate
        binding.albumView.albums = albums

        artist.streamingServices.spotifyUrl?.let { binding.streamOnView.spotify = it }
        artist.streamingServices.appleMusicUrl?.let { binding.streamOnView.appleMusic = it }
        artist.streamingServices.amazonMusicUrl?.let { binding.streamOnView.amazonMusic = it }
        artist.streamingServices.tidalUrl?.let { binding.streamOnView.tidal = it }
        artist.streamingServices.deezerUrl?.let { binding.streamOnView.deezer = it }
        binding.streamOnView.setOnIconClickListener { startActionViewIntent(it) }

        setImages(artist.imageUrl, artist.gender)

        if (artist.imageUrl != null) {
            val color = ContextCompat.getColor(
                requireContext(),
                com.lpirro.core.R.color.md_theme_dark_secondary
            )
            binding.motionLayout.setCustomAttribute(
                R.id.expanded,
                R.id.artist_name,
                "TextColor",
                Color.WHITE
            )
            binding.artistDescription.setTextColor(color)
        }
    }

    private fun setImages(imageUrl: String?, gender: String) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        val errorDrawable = when (gender) {
            "Female" -> ContextCompat.getDrawable(
                requireContext(),
                R.drawable.profile_placeholder_female
            )
            else -> ContextCompat.getDrawable(requireContext(), R.drawable.profile_placeholder_male)
        }

        Glide.with(this)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.profile_placeholder_loading)
            .error(errorDrawable)
            .transition(withCrossFade(factory))
            .into(binding.artistImage)

        Glide.with(this)
            .load(imageUrl)
            .transition(withCrossFade(factory))
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.headerBlurBackground)
    }

    private fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch { viewModel.uiState.collect(::onUiUpdate) }
            }
        }
    }
}
