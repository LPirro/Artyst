package com.lpirro.core.extensions

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.startActionViewIntent(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}
