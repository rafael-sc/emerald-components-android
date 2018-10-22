package br.com.stone.emeraldcomponents.basic.label

import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import br.com.stone.emeraldcomponents.R

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
enum class EmeraldLabelState : LabelStateHandler {
    FILLED {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.run {
                setTextColor(ContextCompat.getColor(context, R.color.emerald_white_1))
                getLabelDrawable(this)?.setColor(color)
            }
        }
    },
    OUTLINE {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.run {
                setTextColor(color)
                getLabelDrawable(this)?.setStroke(1, color)
            }
        }
    },
    TEXT {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.run {
                setTextColor(color)
            }
        }
    },
    TEXT_WITH_IMAGE {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.run {
                setTextColor(color)
            }
        }
    };

    fun getLabelDrawable(label: EmeraldLabel): GradientDrawable? {
        return label.background.mutate() as? GradientDrawable
    }

}

interface LabelStateHandler {
    fun setProperties(label: EmeraldLabel, color: Int)
}
