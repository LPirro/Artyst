package com.lpirro.artist_detail.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.lpirro.artist_detail.databinding.ViewArtistInfoBinding
import com.lpirro.core.extensions.getColorFromAttr

class ArtistInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: ViewArtistInfoBinding

    init {
        binding = ViewArtistInfoBinding.inflate(LayoutInflater.from(context), this, true)
        setCardBackgroundColor(context.getColorFromAttr(com.google.android.material.R.attr.colorPrimary))

        shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, 70f)
            .setBottomRightCorner(CornerFamily.ROUNDED, 70f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 0f)
            .setTopRightCorner(CornerFamily.ROUNDED, 0f)
            .build()
    }

    var country: String = ""
        set(value) {
            field = value
            binding.countryValue.text = value
        }

    var gender: String = ""
        set(value) {
            field = value
            binding.genderValue.text = value
        }

    var birthDate: String = ""
        set(value) {
            field = value
            binding.bornValue.text = value
        }

    override fun setRadius(radius: Float) {
        shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, 0f)
            .setTopRightCorner(CornerFamily.ROUNDED, 0f)
            .build()
    }
}
