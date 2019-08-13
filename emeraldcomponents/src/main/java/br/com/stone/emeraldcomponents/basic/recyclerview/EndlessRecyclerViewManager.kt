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
    var lastPageReached = false
        private set
    var isLoading = false

    init {
        recyclerView.addOnScrollListener(EndlessScrollListener {
            if (!lastPageReached && !isLoading) {
                isLoading = true
                shouldLoadMore(nextPage++)
            }
        })
    }

    fun shouldShowLoading(position: Int): Boolean {
        return !lastPageReached && position == recyclerView.adapter?.itemCount?.minus(1)
    }

    fun defineIfLastPage(newItemsAmount: Int) {
        lastPageReached = pageSize > newItemsAmount
    }

}