package br.com.stone.emeraldcomponents.basic.pager.tabs

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.getActivity
import kotlinx.android.synthetic.main.widget_tab_pager.view.*

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldTabPager : ConstraintLayout {

    init {
        inflate(context, R.layout.widget_tab_pager, this)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAdapter(itemList: List<EmeraldTabItem>) {
        emeraldTabLayout.setupWithViewPager(emeraldViewPager)
        emeraldViewPager.adapter = EmeraldTabPagerAdapter(getActivity(), itemList)
        itemList.forEachIndexed { position, item ->
            item.iconId?.let { emeraldTabLayout.getTabAt(position)?.setIcon(it) }
        }
    }
}