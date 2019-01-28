package br.com.stone.emeraldcomponents.basic.pager.tabs

import android.view.View

/**
 * Created by renan.silva on 18/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldTabItem(layoutId: Int, bindValues: (layout: View) -> Unit, title: String = "", val iconId: Int? = null)
    : EmeraldTabPagerItem(layoutId, bindValues, title)