package com.lpirro.artist_detail.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lpirro.artist_detail.model.AlbumUi

object AlbumDiffCallback : DiffUtil.ItemCallback<AlbumUi>() {

    override fun areItemsTheSame(oldItem: AlbumUi, newItem: AlbumUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumUi, newItem: AlbumUi): Boolean {
        return oldItem == newItem
    }
}
