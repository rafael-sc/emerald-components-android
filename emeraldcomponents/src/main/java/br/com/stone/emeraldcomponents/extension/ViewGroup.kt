package br.com.stone.emeraldcomponents.extension

import android.view.View
import android.view.ViewGroup

/**
 * Created by renan.silva on 04/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
fun ViewGroup.getAllChildren(): List<View> {
    val visited = ArrayList<View>()
    val unvisited = ArrayList<View>()
    unvisited.add(this)

    while (!unvisited.isEmpty()) {
        val child = unvisited.removeAt(0)
        visited.add(child)
        if (child !is ViewGroup) continue
        val childCount = child.childCount
        for (i in 0 until childCount) unvisited.add(child.getChildAt(i))
    }

    return visited
}