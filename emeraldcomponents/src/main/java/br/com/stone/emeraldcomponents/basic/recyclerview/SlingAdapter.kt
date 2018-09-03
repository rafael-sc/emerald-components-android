package br.com.stone.emeraldcomponents.basic.recyclerview

import android.view.View

/**
 * Created by renan.silva on 22/03/2018.
 */

class SlingAdapter<ITEM>(
        private val defineViewType: (Int) -> Int,
        private val bindHolder: View.(ITEM) -> Unit
) : AbstractAdapter<ITEM>() {

    var itemClick: ITEM.() -> Unit = {}

    constructor(defineViewType: (Int) -> Int,
                bindHolder: View.(ITEM) -> Unit,
                itemClick: ITEM.() -> Unit = {}) : this(defineViewType, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }

    override fun getItemViewType(position: Int): Int {
        return defineViewType(position)
    }
}
