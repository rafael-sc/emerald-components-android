package br.com.stone.emeraldcomponents.basics.label

import android.view.View
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.label.EmeraldHideableLabel
import kotlinx.android.synthetic.main.widget_emerald_label.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 23/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldHideableLabelTest {

    lateinit var label: EmeraldHideableLabel

    @Before
    fun setup() {
        label = EmeraldHideableLabel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `should view be created`() {
        Assert.assertNotNull(label)
    }

    @Test
    fun `should view be properly created with its attributes`() {
        val attrs = Robolectric.buildAttributeSet()
                .build()
        val view = EmeraldHideableLabel(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun `should set text properly`() {
        val expected = "test"
        label.text = expected
        assertEquals(expected, label.text)
    }

    @Test
    fun `should hide its value when property is set true`() {
        label.hidden = true
        assertEquals(label.emeraldHideableLabel.visibility, View.INVISIBLE)
    }

    @Test
    fun `should show its value when property is set false`() {
        label.hidden = false
        assertEquals(label.emeraldHideableLabel.visibility, View.VISIBLE)
    }
}