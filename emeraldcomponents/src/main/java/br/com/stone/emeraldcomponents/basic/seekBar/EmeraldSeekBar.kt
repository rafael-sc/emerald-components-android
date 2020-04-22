package br.com.stone.emeraldcomponents.basic.seekBar

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_seek_bar.view.*

//
// Created by daniele.freitas on 22/04/20.
// Copyright (c) 2020 Stone Co. All rights reserved.
//
class EmeraldSeekBar : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_seek_bar, this)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                when(p1) {
                    0 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_zero))
                    1 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_one))
                    2 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_two))
                    3 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_three))
                    4 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_four))
                    5 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_five))
                    6 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_six))
                    7 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_seven))
                    8 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_eight))
                    9 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_nine))
                    10 -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_ten))
                    else -> seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.ic_selector_neutral))
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }
}