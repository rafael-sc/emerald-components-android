package br.com.stone.emeraldcomponents.basic.label

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.show
import kotlinx.android.synthetic.main.widget_emerald_label.view.*
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelIconPosition.START
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelIconPosition.END
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelShape.ROUNDED
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelMargin.SMALL
import br.com.stone.emeraldcomponents.extension.hide

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
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelShape, ROUNDED.ordinal)]
        val margin = EmeraldLabelMargin.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelTextMargin, SMALL.ordinal)]
        setProperties(type, state, size, shape, margin)

        val iconPosition = EmeraldLabelIconPosition.values()[
                args.getInt(R.styleable.EmeraldLabel_emeraldLabelIconPosition, START.ordinal)]
        val icon = args.getResourceId(R.styleable.EmeraldLabel_emeraldLabelIcon, 0)
        if (icon != 0) setIcon(icon, iconPosition)

        args.recycle()
    }

    fun setProperties(type: EmeraldLabelType,
                      state: LabelStateHandler,
                      size: LabelSizeHandler,
                      shape: LabelShapeHandler = ROUNDED,
                      textMargin: LabelMarginHandler = SMALL) {
        this.type = type
        shape.setBackground(this)
        state.setProperties(this, ContextCompat.getColor(context, type.color))
        size.setDimensions(emeraldLabelText)
        textMargin.setMargin(emeraldLabelText)
    }

    fun setCustomColor(state: LabelStateHandler, customColorId: Int) {
        state.setProperties(this, ContextCompat.getColor(context, customColorId))
    }

    fun setIcon(iconResource: Int, position: EmeraldLabelIconPosition = START) {
        val drawable = ContextCompat.getDrawable(context, iconResource)?.mutate()
        drawable?.setColorFilter(ContextCompat.getColor(context, type.color), PorterDuff.Mode.SRC_IN)
        setIcon(drawable, position)
    }

    fun setIcon(icon: Drawable?, position: EmeraldLabelIconPosition = START) {
        when (position) {
            START -> {
                emeraldLabelImage.setImageDrawable(icon)
                emeraldLabelImage.show()
            }
            END -> {
                emeraldLabelImageEnd.setImageDrawable(icon)
                emeraldLabelImageEnd.show()
            }
        }
    }

    fun removeIcons() {
        emeraldLabelImage.hide()
        emeraldLabelImageEnd.hide()
    }
}