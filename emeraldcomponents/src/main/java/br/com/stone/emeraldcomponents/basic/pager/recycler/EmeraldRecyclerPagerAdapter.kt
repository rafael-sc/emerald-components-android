package br.com.stone.emeraldcomponents.basic.pager.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerItem
import br.com.stone.emeraldcomponents.basic.recyclerview.AutoUpdatableAdapter

/**
 * Created by renan.silva on 18/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldRecyclerPagerAdapter(private val itemList: List<EmeraldPagerItem>)
    : RecyclerView.Adapter<EmeraldRecyclerPagerAdapter.Holder>(), AutoUpdatableAdapter {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return Holder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].layoutId
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        item.bindValues(holder.itemView)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}