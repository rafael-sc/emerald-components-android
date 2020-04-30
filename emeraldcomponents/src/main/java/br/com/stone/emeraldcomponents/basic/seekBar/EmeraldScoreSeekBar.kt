package br.com.stone.emeraldcomponents.basic.seekbar

import android.content.Context
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
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)

    var onProgressChanged: (Int) -> Unit = { }

    init {
        changeProgressThumb(-1)

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