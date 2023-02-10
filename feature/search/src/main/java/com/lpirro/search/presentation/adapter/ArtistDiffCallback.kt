package com.lpirro.search.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lpirro.search.model.ArtistUi

object ArtistDiffCallback : DiffUtil.ItemCallback<ArtistUi>() {

    override fun areItemsTheSame(oldItem: ArtistUi, newItem: ArtistUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArtistUi, newItem: ArtistUi): Boolean {
        return oldItem == newItem
    }
}
