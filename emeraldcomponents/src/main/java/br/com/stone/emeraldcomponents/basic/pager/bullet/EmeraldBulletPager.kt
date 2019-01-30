package br.com.stone.emeraldcomponents.basic.pager.bullet

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerAdapter
import br.com.stone.emeraldcomponents.basic.pager.EmeraldViewPager
import br.com.stone.emeraldcomponents.extension.getActivity
import br.com.stone.emeraldcomponents.extension.toDip
import kotlinx.android.synthetic.main.widget_bullet_pager.view.*

/**
 * Created by renan.silva on 25/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldBulletPager : ConstraintLayout {

    init {
        inflate(context, R.layout.widget_bullet_pager, this)
    }

    val viewPager: EmeraldViewPager by lazy { emeraldViewPager }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAdapter(itemList: List<EmeraldBulletPagerItem>, pageWidthPercentage: Float = 1f, pageMargin: Int = 0) {
        emeraldTabLayout.setupWithViewPager(emeraldViewPager)
        emeraldViewPager.adapter = EmeraldPagerAdapter(getActivity(), itemList, pageWidthPercentage)
        emeraldViewPager.pageMargin = pageMargin.toDip(context)
    }
}