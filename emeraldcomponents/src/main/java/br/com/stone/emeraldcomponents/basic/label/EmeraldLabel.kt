package br.com.stone.emeraldcomponents.basic.label

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.show
import kotlinx.android.synthetic.main.widget_emerald_label.view.*

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldLabel : ConstraintLayout {

    val emeraldLabelText: TextView by lazy { emeraldLabelTextLayout as TextView }

    var text: String = ""
        set(newValue) {
            field = newValue.toUpperCase()
            emeraldLabelText.text = field
        }

    private var type: EmeraldLabelType = EmeraldLabelType.SUCCESS

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    init {
        inflate(context, R.layout.widget_emerald_label, this)
    }

    private fun setAttributes(attrs: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.EmeraldLabel, 0, 0)

        text = args.getString(R.styleable.EmeraldLabel_text) ?: ""
        val type = EmeraldLabelType.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelType, EmeraldLabelType.SUCCESS.ordinal)]
        val state = EmeraldLabelState.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelState, EmeraldLabelState.FILLED.ordinal)]
        val size = EmeraldLabelSize.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelSize, EmeraldLabelSize.SMALL.ordinal)]
        val shape = EmeraldLabelShape.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelShape, EmeraldLabelShape.ROUNDED.ordinal)]
        setProperties(type, state, size, shape)

        val icon = args.getResourceId(R.styleable.EmeraldLabel_emeraldLabelIcon, 0)
        if (icon != 0) setIcon(icon)

        args.recycle()
    }

    fun setProperties(type: EmeraldLabelType, state: LabelStateHandler, size: LabelSizeHandler, shape: EmeraldLabelShape = EmeraldLabelShape.ROUNDED) {
        this.type = type
        shape.setBackground(this)
        state.setProperties(this, ContextCompat.getColor(context, type.color))
        size.setDimensions(emeraldLabelText)
    }

    fun setCustomColor(state: LabelStateHandler, customColorId: Int) {
        state.setProperties(this, ContextCompat.getColor(context, customColorId))
    }

    fun setIcon(iconResource: Int) {
        val drawable = ContextCompat.getDrawable(context, iconResource)?.mutate()
        drawable?.setColorFilter(ContextCompat.getColor(context, type.color), PorterDuff.Mode.SRC_IN)
        emeraldLabelImage.setImageDrawable(drawable)
        emeraldLabelImage.show()
    }
}