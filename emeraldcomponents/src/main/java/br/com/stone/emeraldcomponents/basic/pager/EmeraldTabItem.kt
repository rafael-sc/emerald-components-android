package br.com.stone.emeraldcomponents.basic.pager

import android.view.View

/**
 * Created by renan.silva on 18/04/2018.
 */
class EmeraldTabItem(layoutId: Int, bindValues: (layout: View) -> Unit, title: String = "", val iconId: Int? = null)
    : EmeraldPagerItem(layoutId, bindValues, title)