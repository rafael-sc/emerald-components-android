package br.com.stone.emeraldcomponents.basic.label

import android.util.TypedValue
import android.widget.TextView
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.dimen

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
enum class EmeraldLabelSize : LabelSizeHandler {
    SMALL {
        override fun setDimensions(labelText: TextView) {
            labelText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    labelText.context.dimen(R.dimen.emerald_small_text))
        }
    },
    MEDIUM {
        override fun setDimensions(labelText: TextView) {
            labelText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    labelText.context.dimen(R.dimen.emerald_medium_text))
        }
    },
    BIG {
        override fun setDimensions(labelText: TextView) {
            labelText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    labelText.context.dimen(R.dimen.emerald_large_text))
        }
    }
}