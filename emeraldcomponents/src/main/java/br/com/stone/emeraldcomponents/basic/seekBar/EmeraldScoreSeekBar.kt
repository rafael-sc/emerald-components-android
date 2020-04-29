package br.com.stone.emeraldcomponents.basic.seekBar

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R

//
// Created by daniele.freitas on 22/04/20.
// Copyright (c) 2020 Stone Co. All rights reserved.
//
class EmeraldScoreSeekBar : AppCompatSeekBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    var onProgressChanged: (Int) -> Unit = { }

    override fun setThumb(thumb: Drawable?) {
        val thumb = ContextCompat.getDrawable(context, R.drawable.custom_thumb) as LayerDrawable
        super.setThumb(thumb)
    }

    private fun init() {
        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, p2: Boolean) {
                changeProgressThumb(progress)
                onProgressChanged(progress)
            }

            override fun onStartTrackingTouch(seekbar: SeekBar?) {}

            override fun onStopTrackingTouch(seekbar: SeekBar?) {}
        })
    }

    private fun changeProgressThumb(progress: Int) {
        val scoreEnum = ScoreSeekBarEnum.getScoreImageByProgress(progress)
        val bgDrawable = ContextCompat.getDrawable(context, R.drawable.custom_thumb) as LayerDrawable
        bgDrawable.setDrawableByLayerId(R.id.customThumb, ContextCompat.getDrawable(context, scoreEnum.iconId))

        super.setThumb(bgDrawable)
    }
}