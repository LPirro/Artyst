package com.lpirro.core.ui.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This class adds Vertical spacing on the RecyclerView
 *
 * @property spaceSize the spacing in dp between the elements
 * @property edgeSpacing the spacing in dp for the first element and last element of the RecyclerView
 */

class VerticalSpaceItemDecoration(
    private val spaceSize: Int,
    private val edgeSpacing: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val currentPosition = parent.getChildAdapterPosition(view)
        val isFirstPosition = currentPosition == 0
        val isLastPosition = state.itemCount > 0 && currentPosition == state.itemCount - 1
        val isOnlyOneItem = state.itemCount == 1

        with(outRect) {
            when {
                isOnlyOneItem -> {
                    top = edgeSpacing
                    bottom = edgeSpacing
                }
                isFirstPosition -> {
                    top = edgeSpacing
                    bottom = spaceSize / 2
                }
                isLastPosition -> {
                    top = spaceSize / 2
                    bottom = edgeSpacing
                }
                else -> {
                    top = spaceSize / 2
                    bottom = spaceSize / 2
                }
            }
        }
    }
}
