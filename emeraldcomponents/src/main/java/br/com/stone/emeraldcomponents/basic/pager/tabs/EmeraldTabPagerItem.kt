package br.com.stone.emeraldcomponents.basic.pager.tabs

import android.view.View
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerItem

/**
 * Created by renan.silva on 18/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldTabPagerItem(override val layoutId: Int,
                          override val bindValues: (layout: View) -> Unit,
                          override val title: String = "",
                          override val iconId: Int? = null) : EmeraldPagerItem