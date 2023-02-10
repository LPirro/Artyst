package com.lpirro.artist_detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lpirro.artist_detail.R
import com.lpirro.artist_detail.databinding.ItemAlbumBinding
import com.lpirro.artist_detail.model.AlbumUi

class AlbumAdapter : ListAdapter<AlbumUi, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.binding.name.text = album.title
        holder.binding.releaseDate.text = album.releaseDate

        Glide.with(holder.itemView.context)
            .load(album.coverUrl)
            .placeholder(R.drawable.album_cover_placeholder)
            .error(R.drawable.cover_error)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.albumCoverImage)
    }

    inner class AlbumViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)
}
