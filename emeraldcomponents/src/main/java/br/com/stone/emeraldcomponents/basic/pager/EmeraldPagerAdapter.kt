package br.com.stone.emeraldcomponents.basic.pager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by renan.silva on 18/04/2018.
 */
class EmeraldPagerAdapter(private val context: Context, private val itemList: List<EmeraldPagerItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = itemList[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(item.layoutId, container, false) as ViewGroup
        container.addView(layout)
        item.bindValues(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = itemList.size
}