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
    var isLoading = false

    init {
        recyclerView.addOnScrollListener(EndlessScrollListener {
            if (!lastPageReached && !isLoading) {
                isLoading = true
                shouldLoadMore(pageToLoad)
            }
        })
    }

    fun shouldShowLoading(position: Int): Boolean {
        return !lastPageReached && position == recyclerView.adapter?.itemCount?.minus(1)
    }

}