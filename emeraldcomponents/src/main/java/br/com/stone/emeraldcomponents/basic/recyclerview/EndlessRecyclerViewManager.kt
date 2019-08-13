package br.com.stone.emeraldcomponents.basic.recyclerview

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by renan.silva on 12/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EndlessRecyclerViewManager(private val recyclerView: RecyclerView,
                                 private val pageSize: Int,
                                 private val shouldLoadMore: (pageToLoad: Int) -> Unit) {

    private var nextPage = 1
    private var lastPageReached = false
    private var isLoading = false

    init {
        recyclerView.addOnScrollListener(EndlessScrollListener {
            if (!lastPageReached && !isLoading) {
                isLoading = true
                shouldLoadMore(nextPage++)
            }
        })
    }

    fun <ITEM> addItems(itemsToAdd: List<ITEM>) {
        lastPageReached = isLastPage(itemsToAdd.size)

        val newItems = (recyclerView.adapter as AbstractAdapter<ITEM>).itemList.toMutableSet()
        newItems.addAll(itemsToAdd)
        val newItemsList = if (lastPageReached) {
            newItems.toList()
        } else {
            newItems.toMutableList().apply { add(newItems.first()) }
        }

        (recyclerView.adapter as AbstractAdapter<ITEM>).itemList = newItemsList
        isLoading = false
    }

    fun shouldShowLoading(position: Int): Boolean {
        return !lastPageReached && position == recyclerView.adapter?.itemCount?.minus(1)
    }

    private fun isLastPage(newItemsAmount: Int): Boolean {
        return pageSize > newItemsAmount
    }

}