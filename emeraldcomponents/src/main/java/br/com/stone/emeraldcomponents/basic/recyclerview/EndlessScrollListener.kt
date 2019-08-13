package br.com.stone.emeraldcomponents.basic.recyclerview

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by renan.silva on 12/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EndlessScrollListener(val onEndReached: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (!recyclerView.canScrollVertically(1)) {
            onEndReached()
        }
    }

}