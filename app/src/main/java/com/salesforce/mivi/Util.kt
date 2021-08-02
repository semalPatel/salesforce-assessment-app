package com.salesforce.mivi

import android.content.Context
import android.util.TypedValue

object Util {

    fun dpToPixel(
        dip: Float,
        context: Context): Float {
        val resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            resources.displayMetrics)
    }
}