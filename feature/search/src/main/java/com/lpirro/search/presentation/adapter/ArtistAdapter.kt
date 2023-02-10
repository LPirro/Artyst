package com.lpirro.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lpirro.search.databinding.ItemArtistBinding
import com.lpirro.search.model.ArtistUi

class ArtistAdapter(
    private val artistClickListener: (artistId: String) -> Unit
) : ListAdapter<ArtistUi, ArtistAdapter.ArtistViewHolder>(ArtistDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = getItem(position)
        holder.binding.name.text = artist.name
        holder.binding.type.text = artist.type
        holder.itemView.setOnClickListener { artistClickListener.invoke(artist.id) }
    }

    inner class ArtistViewHolder(val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root)
}
