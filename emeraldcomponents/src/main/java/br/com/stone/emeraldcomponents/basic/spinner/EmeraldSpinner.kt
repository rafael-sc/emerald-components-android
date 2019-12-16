package br.com.stone.emeraldcomponents.basic.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import br.com.stone.emeraldcomponents.R

/**
 * Created by renan.silva on 18/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldSpinner : AppCompatSpinner {

    var placeholder: String? = ""

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    private fun setAttributes(attributeSet: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attributeSet, R.styleable.EmeraldSpinner, 0, 0)

        placeholder = args.getString(R.styleable.EmeraldSpinner_emeraldSpinnerPlaceholder)

        args.recycle()
    }

    fun setItems(dataList: List<Any>, layout: Int = android.R.layout.simple_dropdown_item_1line) {
        val newAdapter = if (placeholder.isNullOrEmpty()) {
            ArrayAdapter<Any>(context, layout, dataList)
        } else {

            val mutableList: MutableList<Any> = mutableListOf(placeholder as Any)
            mutableList.addAll(dataList)
            EmeraldSpinnerArrayAdapter(context, layout, mutableList.toList())
        }

        adapter = newAdapter
    }

    override fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        super.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                listener?.onNothingSelected(parent)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (placeholder.isNullOrEmpty() || position != 0) listener?.onItemSelected(parent, view, position, id)
            }
        })
    }
}