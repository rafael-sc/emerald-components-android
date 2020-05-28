package br.com.stone.emeraldcomponents.basic.label

import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R

/**
 * Created by victor.cruz on 26/05/2020.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
enum class EmeraldLabelShape : LabelShapeHandler {
    ROUNDED {
        override fun setBackground(label: EmeraldLabel) {
            label.background = ContextCompat.getDrawable(label.context, R.drawable.label_border)
        }
    },
    SQUARED {
        override fun setBackground(label: EmeraldLabel) {
            label.background = ContextCompat.getDrawable(label.context, R.drawable.label_border_squared)
        }
    };
}