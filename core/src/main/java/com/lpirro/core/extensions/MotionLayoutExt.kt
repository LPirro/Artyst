package com.lpirro.core.extensions

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintAttribute

fun MotionLayout.setCustomAttribute(
    constraintSetId: Int,
    viewId: Int,
    attribute: String,
    value: Int
) {
    this.getConstraintSet(constraintSetId).getConstraint(viewId)?.let {
        it.mCustomConstraints[attribute] = ConstraintAttribute(
            attribute,
            ConstraintAttribute.AttributeType.COLOR_TYPE,
            value,
            false
        )
    }
}
