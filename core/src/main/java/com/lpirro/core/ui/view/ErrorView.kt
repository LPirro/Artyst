package com.lpirro.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lpirro.core.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewErrorBinding
    var retryClickListener: (() -> Unit)? = null

    init {
        binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
        binding.retryButton.setOnClickListener { retryClickListener?.invoke() }
    }
}
