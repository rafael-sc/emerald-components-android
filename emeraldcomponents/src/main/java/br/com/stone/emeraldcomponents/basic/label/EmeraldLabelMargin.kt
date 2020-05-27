package br.com.stone.emeraldcomponents.basic.label

import android.view.ViewGroup
import android.widget.TextView
import br.com.stone.emeraldcomponents.R

/**
 * Created by victor.cruz on 27/05/2020.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
enum class EmeraldLabelMargin : LabelMarginHandler {
    SMALL {
        override fun setMargin(labelText: TextView) {}
    },
    MEDIUM {
        override fun setMargin(labelText: TextView) {
            applyMarginToTextView(labelText, R.dimen.label_margin_top_medium, R.dimen.label_margin_start_medium)
        }
    },
    BIG {
        override fun setMargin(labelText: TextView) {
            applyMarginToTextView(labelText, R.dimen.label_margin_top_big, R.dimen.label_margin_start_big)
        }
    };

    internal fun applyMarginToTextView(labelText: TextView, marginTopResId: Int, marginStartResId: Int) {
        labelText.run {
            layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
                val marginTop = resources.getDimensionPixelSize(marginTopResId)
                val marginSides = resources.getDimensionPixelSize(marginStartResId)
                setMargins(marginSides, marginTop, marginSides, marginTop)
                marginStart = marginSides
                marginEnd = marginSides
            }
        }
    }
}