package com.lpirro.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lpirro.core.R
import com.lpirro.core.databinding.ViewSearchEmptyBinding

class SearchEmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewSearchEmptyBinding

    init {
        binding = ViewSearchEmptyBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var query: String = ""
        set(value) {
            field = value
            binding.emptyViewText.text = resources.getString(R.string.empty_search_text, value)
        }
}
