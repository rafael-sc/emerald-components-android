package br.com.stone.emeraldcomponents.basics.label

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabel
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelShape
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

/**
 * Created by victor.cruz on 27/05/2020.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldLabelShapeTest {

    lateinit var label: EmeraldLabel
    val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        label = EmeraldLabel(context)
    }

    @Test
    fun `Should set round shape`() {
        EmeraldLabelShape.ROUNDED.setBackground(label)
        assertEquals(R.drawable.label_border, Shadows.shadowOf(label.background).createdFromResId)
    }

    @Test
    fun `Should set square shape`() {
        EmeraldLabelShape.SQUARED.setBackground(label)
        assertEquals(R.drawable.label_border_squared, Shadows.shadowOf(label.background).createdFromResId)
    }
}