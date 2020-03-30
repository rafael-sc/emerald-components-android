package br.com.stone.emeraldcomponents.basic.pager.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.widget_tab_recycler_pager.view.*

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldRecyclerTabPager : ConstraintLayout {

    val tabLayout: TabLayout by lazy { emeraldTabLayout }

    init {
        inflate(context, R.layout.widget_tab_recycler_pager, this)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAdapter(itemList: List<EmeraldTabPagerItem>) {
        emeraldViewPager.adapter = EmeraldRecyclerPagerAdapter(itemList)
        emeraldViewPager
        TabLayoutMediator(tabLayout, emeraldViewPager) { tab, position ->
            tab.text = itemList[position].title
        }.attach()

        itemList.forEachIndexed { position, item ->
            item.iconId?.let { emeraldTabLayout.getTabAt(position)?.setIcon(it) }
        }
    }

    fun selectTab(index: Int) {
        emeraldViewPager.setCurrentItem(index, true)
    }
}