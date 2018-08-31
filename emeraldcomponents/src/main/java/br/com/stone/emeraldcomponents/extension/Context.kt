package br.com.stone.emeraldcomponents.extension

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.colorRes(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.dimen(dimenResId: Int): Float {
    return resources.getDimension(dimenResId)
}