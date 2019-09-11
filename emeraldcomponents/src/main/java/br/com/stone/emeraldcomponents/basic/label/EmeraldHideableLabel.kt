package br.com.stone.emeraldcomponents.basic.label

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_emerald_hideable_label.view.*

/**
 * Created by lucas on 11/09/19.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class EmeraldHideableLabel : ConstraintLayout {

    val emeraldHideableLabel: TextView by lazy { textView4 }

    var text: String = ""
        set(newValue) {
            field = newValue
            emeraldHideableLabel.text = field
        }

    var hidden: Boolean = false
        set(newValue) {
            field = newValue
            hiddenView.visibility = if (field) View.VISIBLE else View.GONE
            emeraldHideableLabel.visibility = if (field) View.INVISIBLE else View.VISIBLE
        }

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.EmeraldHideableLabel, 0, 0)
        hidden = args.getBoolean(R.styleable.EmeraldHideableLabel_hidden, false)
        text = args.getString(R.styleable.EmeraldHideableLabel_text) ?: ""

        args.recycle()
    }

    init {
        inflate(context, R.layout.widget_emerald_hideable_label, this)
    }
}