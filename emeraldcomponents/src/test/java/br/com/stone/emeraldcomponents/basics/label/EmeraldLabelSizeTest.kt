package br.com.stone.emeraldcomponents.basics.label

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.TextView
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelSize
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by renan.silva on 24/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldLabelSizeTest {

    lateinit var textView: TextView
    var smallSize = 1f
    var mediumSize = 2f
    var bigSize = 3f

    @Before
    fun setup() {
        textView = mock(TextView::class.java)
        val resources = mock(Resources::class.java)
        `when`(resources.getDimension(R.dimen.emerald_small_text)).thenReturn(smallSize)
        `when`(resources.getDimension(R.dimen.emerald_medium_text)).thenReturn(mediumSize)
        `when`(resources.getDimension(R.dimen.emerald_large_text)).thenReturn(bigSize)

        val context = mock(Context::class.java)
        `when`(context.resources).thenReturn(resources)
        `when`(textView.context).thenReturn(context)
    }

    @Test
    fun testSetDimensionsSmall() {
        EmeraldLabelSize.SMALL.setDimensions(textView)
        verify(textView).setTextSize(TypedValue.COMPLEX_UNIT_PX, smallSize)
    }

    @Test
    fun testSetDimensionsMedium() {
        EmeraldLabelSize.MEDIUM.setDimensions(textView)
        verify(textView).setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumSize)
    }

    @Test
    fun testSetDimensionsBig() {
        EmeraldLabelSize.BIG.setDimensions(textView)
        verify(textView).setTextSize(TypedValue.COMPLEX_UNIT_PX, bigSize)
    }
}