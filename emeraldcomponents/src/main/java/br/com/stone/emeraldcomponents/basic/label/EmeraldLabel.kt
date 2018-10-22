package br.com.stone.emeraldcomponents.basic.label

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import br.com.stone.emeraldcomponents.R


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

    init {
        background = ContextCompat.getDrawable(context, R.drawable.label_border)
    }

    private fun setAttributes(attrs: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.EmeraldLabel, 0, 0)

        val type = EmeraldLabelType.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelType, EmeraldLabelType.SUCCESS.ordinal)]
        val state = EmeraldLabelState.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelState, EmeraldLabelState.FILLED.ordinal)]
        val size = EmeraldLabelSize.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelSize, EmeraldLabelSize.SMALL.ordinal)]

        setProperties(type, state, size)
        args.recycle()
    }

    private fun setProperties(type: EmeraldLabelType, state: EmeraldLabelState, size: EmeraldLabelSize) {
        state.setProperties(this, ContextCompat.getColor(context, type.color))
    }
}