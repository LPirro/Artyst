package com.lpirro.artist_detail.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.lpirro.artist_detail.databinding.ViewStreamOnBinding

class StreamOnView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: ViewStreamOnBinding

    init {
        binding = ViewStreamOnBinding.inflate(LayoutInflater.from(context), this, true)
        elevation = 0f
    }

    var listener: ((serviceUrl: String) -> Unit)? = null

    var spotify: String = ""
        set(value) {
            field = value
            binding.spotifyIcon.alpha = 1f
            binding.spotifyIcon.setOnClickListener { listener?.invoke(value) }
        }

    var appleMusic: String = ""
        set(value) {
            field = value
            binding.appleMusicIcon.alpha = 1f
            binding.appleMusicIcon.setOnClickListener { listener?.invoke(value) }
        }

    var amazonMusic: String = ""
        set(value) {
            field = value
            binding.amazonMusicIcon.alpha = 1f
            binding.amazonMusicIcon.setOnClickListener { listener?.invoke(value) }
        }

    var tidal: String = ""
        set(value) {
            field = value
            binding.tidalIcon.alpha = 1f
            binding.tidalIcon.setOnClickListener { listener?.invoke(value) }
        }

    var deezer: String = ""
        set(value) {
            field = value
            binding.deezerIcon.alpha = 1f
            binding.deezerIcon.setOnClickListener { listener?.invoke(value) }
        }

    fun setOnIconClickListener(block: (serviceUrl: String) -> Unit) {
        listener = block
    }
}
