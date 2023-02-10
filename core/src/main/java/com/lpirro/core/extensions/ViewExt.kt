package com.lpirro.core.extensions

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) = when (value) {
        true -> visibility = View.VISIBLE
        false -> visibility = View.GONE
    }

var ContentLoadingProgressBar.loading: Boolean
    get() = this.isVisible
    set(value) = when (value) {
        true -> this.show()
        false -> this.hide()
    }

fun EditText.textInputAsFlow() = callbackFlow {
    val watcher: TextWatcher = doOnTextChanged { textInput: CharSequence?, _, _, _ ->
        trySend(textInput.toString())
    }
    awaitClose { this@textInputAsFlow.removeTextChangedListener(watcher) }
}
