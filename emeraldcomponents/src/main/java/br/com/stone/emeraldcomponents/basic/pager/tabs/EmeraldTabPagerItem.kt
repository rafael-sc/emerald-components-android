package br.com.stone.emeraldcomponents.basic.pager.tabs

import android.view.View

/**
 * Created by renan.silva on 18/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
open class EmeraldTabPagerItem(val layoutId: Int, val bindValues: (layout: View) -> Unit, val title: String = "")