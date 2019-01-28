package br.com.stone.emeraldcomponents.basic.pager

import android.view.View

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
interface EmeraldPagerItem {
    val layoutId: Int
    val bindValues: (layout: View) -> Unit
    val title: String
}