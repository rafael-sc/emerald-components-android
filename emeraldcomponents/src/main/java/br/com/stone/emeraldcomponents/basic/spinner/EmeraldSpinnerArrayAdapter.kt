package br.com.stone.emeraldcomponents.basic.spinner

import android.content.Context
import android.graphics.Color
import androidx.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by victor.cruz on 04/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
class EmeraldSpinnerArrayAdapter(context: Context, @LayoutRes layout: Int, dataList: List<Any>) :
        ArrayAdapter<Any>(context, layout, dataList) {

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        return setTextColorBasedOnPosition(view, position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent) as TextView
        return setTextColorBasedOnPosition(view, position)
    }

    private fun setTextColorBasedOnPosition(view: TextView, position: Int): TextView {
        return view.apply {
            if (position == 0) setTextColor(Color.GRAY) else setTextColor(Color.BLACK)
        }
    }
}