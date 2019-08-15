package br.com.stone.emeraldcomponents.basic.recyclerview

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by renan.silva on 12/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EndlessRecyclerViewManager(private val recyclerView: RecyclerView,
                                 var pageToLoad: Int = 0,
                                 private val shouldLoadMore: (pageToLoad: Int) -> Unit) {

    var lastPageReached = false
    var isLoading = true
        set(value) {
            field = value
            @Suppress("UNCHECKED_CAST")
            val adapter = recyclerView.adapter as AbstractAdapter<Any>
            if (field) addLoading(adapter) else removeLoading(adapter)
        }

    init {
        recyclerView.addOnScrollListener(EndlessScrollListener {
            if (!lastPageReached && !isLoading) {
                isLoading = true
                shouldLoadMore(pageToLoad)
            }
        })
    }

    fun shouldShowLoading(position: Int): Boolean {
        return !lastPageReached
                && position == recyclerView.adapter?.itemCount?.minus(1)
                && isLoading
    }


    fun <ITEM> addItems(itemsToAdd: List<ITEM>,
                        abstractAdapter: AbstractAdapter<ITEM>,
                        isLastPage: Boolean = false) {
        lastPageReached = isLastPage
        isLoading = false
        pageToLoad++

        val newItems = abstractAdapter.itemList.toMutableSet()
        newItems.addAll(itemsToAdd)

        abstractAdapter.itemList = newItems.toList()
    }

    private fun addLoading(adapter: AbstractAdapter<Any>) {
        val currentList = adapter.itemList.toMutableList()
        val newItems = currentList.apply { add(currentList.first()) }
        adapter.itemList = newItems
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }

    private fun removeLoading(adapter: AbstractAdapter<Any>) {
        if (adapter.itemList.isEmpty()) return
        val loadingPosition = adapter.itemCount - 1
        val currentList = adapter.itemList.toMutableList()
        val newItems = currentList.apply { removeAt(loadingPosition) }
        adapter.itemList = newItems
    }
}