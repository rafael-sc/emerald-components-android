package br.com.stone.emeraldcomponents.basics.label

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabel
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelState
import kotlinx.android.synthetic.main.widget_emerald_label.view.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 24/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldLabelStateTest {

    lateinit var label: EmeraldLabel
    lateinit var background: GradientDrawable
    val testColor = 0

    @Before
    fun setup() {
        label = mock(EmeraldLabel::class.java)
        val textView = mock(TextView::class.java)
        `when`(textView.context).thenReturn(ApplicationProvider.getApplicationContext())
        `when`(label.emeraldLabelText).thenReturn(textView)

        background = mock(GradientDrawable::class.java)
        `when`(label.background).thenReturn(background)
        `when`(label.background.mutate()).thenReturn(background)
    }

    @Test
    fun testSetPropertiesFilled() {
        EmeraldLabelState.FILLED.setProperties(label, testColor)
        verify(background).setColor(testColor)
    }

    @Test
    fun testSetPropertiesOutline() {
        val testBorderWidth = 1

        val resources = mock(Resources::class.java)
        `when`(resources.getDimension(R.dimen.label_border_width)).thenReturn(testBorderWidth.toFloat())
        val context = mock(Context::class.java)
        `when`(context.resources).thenReturn(resources)

        EmeraldLabelState.OUTLINE.setProperties(label, testColor)
        verify(background).setStroke(testBorderWidth, testColor)
    }

    @Test
    fun testSetPropertiesText() {
        val label = EmeraldLabel(ApplicationProvider.getApplicationContext())
        EmeraldLabelState.TEXT.setProperties(label, testColor)
        assertEquals(View.VISIBLE, label.emeraldLabelImage.visibility)
    }

    @Test
    fun testGetLabelBackgroundDrawable() {
        val label = EmeraldLabel(ApplicationProvider.getApplicationContext())
        val background = EmeraldLabelState.TEXT.getLabelBackgroundDrawable(label)
        assertNotNull(background)
    }
}