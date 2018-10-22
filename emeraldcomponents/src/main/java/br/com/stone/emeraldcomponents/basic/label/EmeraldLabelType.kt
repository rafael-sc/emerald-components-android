package br.com.stone.emeraldcomponents.basic.label

import br.com.stone.emeraldcomponents.R

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
enum class EmeraldLabelType(val color: Int) {
    ERROR(R.color.emerald_error),
    WARNING(R.color.emerald_attention),
    NEUTRAL(R.color.emerald_white_4),
    SUCCESS(R.color.emerald_success),
    INFO(R.color.emerald_secondary);
}