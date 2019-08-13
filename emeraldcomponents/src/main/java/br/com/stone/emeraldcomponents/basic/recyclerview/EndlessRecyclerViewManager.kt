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
    private var isLoading = false
    private var lastPageReached = false

    init {
        recyclerView.addOnScrollListener(EndlessScrollListener {
            if (!lastPageReached && !isLoading) {
                isLoading = true
                shouldLoadMore(nextPage++)
            }
        })
    }

    fun <ITEM> addItems(itemsToAdd: List<ITEM>) {
        checkIfIsLastPage(itemsToAdd.size)

        val newItems = (recyclerView.adapter as AbstractAdapter<ITEM>).itemList.toMutableSet()
        newItems.addAll(itemsToAdd)
        (recyclerView.adapter as AbstractAdapter<ITEM>).itemList = newItems.toList()
        isLoading = false
    }

    private fun checkIfIsLastPage(newItemsAmount: Int) {
        lastPageReached = pageSize > newItemsAmount
    }

}