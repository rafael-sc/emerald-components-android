package br.com.stone.emeraldcomponents.basics.label

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabel
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelMargin
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by victor.cruz on 27/05/2020.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldLabelMarginTest {

    lateinit var textView: TextView
    val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        textView = EmeraldLabel(context).emeraldLabelText
    }

    @Test
    fun `Should set small margins`() {
        EmeraldLabelMargin.SMALL.setMargin(textView)

        val layoutParams = textView.layoutParams as ViewGroup.MarginLayoutParams
        val startMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_start_small)
        val topMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_top_small)
        assertEquals(startMargin, layoutParams.marginStart)
        assertEquals(startMargin, layoutParams.marginEnd)
        assertEquals(topMargin, layoutParams.topMargin)
        assertEquals(topMargin, layoutParams.bottomMargin)
    }

    @Test
    fun `Should set medium margins`() {
        EmeraldLabelMargin.MEDIUM.setMargin(textView)

        val layoutParams = textView.layoutParams as ViewGroup.MarginLayoutParams
        val startMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_start_medium)
        val topMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_top_medium)
        assertEquals(startMargin, layoutParams.marginStart)
        assertEquals(startMargin, layoutParams.marginEnd)
        assertEquals(topMargin, layoutParams.topMargin)
        assertEquals(topMargin, layoutParams.bottomMargin)
    }

    @Test
    fun `Should set big margins`() {
        EmeraldLabelMargin.BIG.setMargin(textView)

        val layoutParams = textView.layoutParams as ViewGroup.MarginLayoutParams
        val startMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_start_big)
        val topMargin = context.resources.getDimensionPixelSize(R.dimen.label_margin_top_big)
        assertEquals(startMargin, layoutParams.marginStart)
        assertEquals(startMargin, layoutParams.marginEnd)
        assertEquals(topMargin, layoutParams.topMargin)
        assertEquals(topMargin, layoutParams.bottomMargin)
    }
}