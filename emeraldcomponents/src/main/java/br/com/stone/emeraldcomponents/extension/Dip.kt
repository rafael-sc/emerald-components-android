package br.com.stone.emeraldcomponents.extension

import android.content.Context
import android.util.TypedValue


/**
 * Created by renan.silva on 30/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
fun Float.toDip(context: Context): Float {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)
}

fun Int.toDip(context: Context): Int {
    return this.toFloat().toDip(context).toInt()
}