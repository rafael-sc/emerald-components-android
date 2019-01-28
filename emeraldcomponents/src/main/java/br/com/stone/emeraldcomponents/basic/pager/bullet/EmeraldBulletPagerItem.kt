package br.com.stone.emeraldcomponents.basic.pager.bullet

import android.view.View
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerItem

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldBulletPagerItem(override val layoutId: Int,
                             override val bindValues: (layout: View) -> Unit) : EmeraldPagerItem {

    override val title: String = ""
}