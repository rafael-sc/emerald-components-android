package br.com.stone.emeraldcomponents.basics.label

import android.content.res.Resources
import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabel
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelType
import br.com.stone.emeraldcomponents.basic.label.LabelSizeHandler
import br.com.stone.emeraldcomponents.basic.label.LabelStateHandler
import kotlinx.android.synthetic.main.widget_emerald_label.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 23/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldLabelTest {

    lateinit var label: EmeraldLabel

    @Before
    fun setup() {
        label = EmeraldLabel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldLabel(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .build()
        val view = EmeraldLabel(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetText() {
        val testValue = "test"
        val expectedValue = "TEST"
        label.text = testValue
        assertEquals(expectedValue, label.text)
        assertEquals(expectedValue, label.emeraldLabelText.text)
    }

    @Test
    fun testSetProperties() {
        val typeMock = mock(EmeraldLabelType::class.java)
        val stateMock = mock(LabelStateHandler::class.java)
        val sizeMock = mock(LabelSizeHandler::class.java)
        val colorResource = android.R.color.black
        `when`(typeMock.color).thenReturn(colorResource)

        label.setProperties(typeMock, stateMock, sizeMock)

        verify(typeMock).color
        verify(stateMock).setProperties(label,
                ContextCompat.getColor(ApplicationProvider.getApplicationContext(), colorResource))
        verify(sizeMock).setDimensions(label.emeraldLabelText)
    }

    @Test
    fun testSetCustomColor() {
        val customColor = android.R.color.black
        val stateMock = mock(LabelStateHandler::class.java)

        label.setCustomColor(stateMock, customColor)

        verify(stateMock).setProperties(label,
                ContextCompat.getColor(ApplicationProvider.getApplicationContext(), customColor))
    }

    @Test(expected = Resources.NotFoundException::class)
    fun testSetPropertiesWithInvalidResource() {
        val typeMock = mock(EmeraldLabelType::class.java)
        val stateMock = mock(LabelStateHandler::class.java)
        val sizeMock = mock(LabelSizeHandler::class.java)

        label.setProperties(typeMock, stateMock, sizeMock)
    }

    @Test
    fun testSetIcon() {
        val iconResource = R.drawable.ic_add_circle_white_24dp
        label.setIcon(iconResource)
        assertEquals(View.VISIBLE, label.emeraldLabelImage.visibility)
    }
}