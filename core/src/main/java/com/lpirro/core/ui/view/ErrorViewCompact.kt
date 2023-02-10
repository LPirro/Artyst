package com.lpirro.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lpirro.core.databinding.ViewErrorCompactBinding

class ErrorViewCompact @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewErrorCompactBinding

    init {
        binding = ViewErrorCompactBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
