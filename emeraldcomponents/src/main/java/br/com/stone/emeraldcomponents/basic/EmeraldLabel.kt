package br.com.stone.emeraldcomponents.basic

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldLabel : TextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    private fun setAttributes(attrs: AttributeSet) {

    }

    init {

    }
}