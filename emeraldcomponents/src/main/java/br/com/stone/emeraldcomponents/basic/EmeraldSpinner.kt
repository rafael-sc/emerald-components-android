package br.com.stone.emeraldcomponents.basic

import android.content.Context
import android.support.v7.widget.AppCompatSpinner
import android.util.AttributeSet
import android.widget.ArrayAdapter

/**
 * Created by renan.silva on 18/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldSpinner : AppCompatSpinner {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun <T> setItems(dataList: List<T>) {
        val newAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, dataList)
        adapter = newAdapter
    }
}