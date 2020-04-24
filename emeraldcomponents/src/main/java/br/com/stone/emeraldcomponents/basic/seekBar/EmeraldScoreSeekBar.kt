package br.com.stone.emeraldcomponents.basic.seekBar

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_score_seek_bar.view.*

//
// Created by daniele.freitas on 22/04/20.
// Copyright (c) 2020 Stone Co. All rights reserved.
//
class EmeraldScoreSeekBar : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    var setChangeProgress: (Int) -> Unit = {  }
    val bgDrawable = ContextCompat.getDrawable(context, R.drawable.custom_thumb) as LayerDrawable

    init {
        inflate(context, R.layout.widget_score_seek_bar, this)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                changeProgressThumb(p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun changeProgressThumb(progress: Int) {
        when(progress) {
            0 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_zero))
            1 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_one))
            2 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_two))
            3 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_three))
            4 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_four))
            5 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_five))
            6 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_six))
            7 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_seven))
            8 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_eight))
            9 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_nine))
            10 -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_ten))
            else -> bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, R.drawable.ic_selector_neutral))
        }
        seekBar.thumb = bgDrawable

        if (progress <= 10) {
            setChangeProgress(progress)
        }
    }
}