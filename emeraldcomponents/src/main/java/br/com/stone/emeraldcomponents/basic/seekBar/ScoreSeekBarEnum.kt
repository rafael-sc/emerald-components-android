package br.com.stone.emeraldcomponents.basic.seekBar

import br.com.stone.emeraldcomponents.R
/**
 * Created by daniele.freitas on 27/04/20.
 * Copyright (c) Stone Co. All rights reserved.
 * daniele.freitas@stone.com.br
 */
internal enum class ScoreSeekBarEnum(val value: Int, val iconId: Int) {

    SCORE_ZERO(0, R.drawable.ic_selector_score_zero),
    SCORE_ONE(1, R.drawable.ic_selector_score_one),
    SCORE_TWO(2, R.drawable.ic_selector_score_two),
    SCORE_THREE(3, R.drawable.ic_selector_score_three),
    SCORE_FOUR(4, R.drawable.ic_selector_score_four),
    SCORE_FIVE(5, R.drawable.ic_selector_score_five),
    SCORE_SIX(6, R.drawable.ic_selector_score_six),
    SCORE_SEVEN(7, R.drawable.ic_selector_score_seven),
    SCORE_EIGHT(8, R.drawable.ic_selector_score_eight),
    SCORE_NINE(9, R.drawable.ic_selector_score_nine),
    SCORE_THEN(10, R.drawable.ic_selector_score_ten),
    NEUTRAL(-1, R.drawable.ic_selector_neutral);

    companion object {
        fun getScoreImageByProgress(value: Int): ScoreSeekBarEnum {
            return values().find { it.value == value } ?: NEUTRAL
        }
    }
}