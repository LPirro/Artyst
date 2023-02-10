package com.lpirro.artist_detail.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.lpirro.artist_detail.R
import com.lpirro.artist_detail.databinding.ViewAlbumBinding
import com.lpirro.artist_detail.model.AlbumUi
import com.lpirro.artist_detail.presentation.adapter.AlbumAdapter
import com.lpirro.core.extensions.visible
import com.lpirro.core.ui.decorators.VerticalSpaceItemDecoration

class AlbumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: ViewAlbumBinding
    private lateinit var albumAdapter: AlbumAdapter

    init {
        binding = ViewAlbumBinding.inflate(LayoutInflater.from(context), this, true)
        elevation = 0f
        setupRecyclerView()
    }

    var albums: List<AlbumUi> = emptyList()
        set(value) {
            field = value
            albumAdapter.submitList(value)
            binding.noAlbumsText.visible = field.isEmpty()
        }

    private fun setupRecyclerView() {
        val edgeSpacing = context.resources.getDimensionPixelSize(R.dimen.album_spacing)
        val spacing = context.resources.getDimensionPixelSize(R.dimen.album_spacing)
        albumAdapter = AlbumAdapter()
        binding.recyclerviewAlbums.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
            addItemDecoration(VerticalSpaceItemDecoration(edgeSpacing, spacing))
        }
    }
}
