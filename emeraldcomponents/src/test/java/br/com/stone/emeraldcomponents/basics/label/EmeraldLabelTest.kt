package br.com.stone.emeraldcomponents.basics.label

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabel
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelIconPosition
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelType
import br.com.stone.emeraldcomponents.basic.label.LabelMarginHandler
import br.com.stone.emeraldcomponents.basic.label.LabelShapeHandler
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
    val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        label = EmeraldLabel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Should instance with context`() {
        val view = EmeraldLabel(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun `Should instance with attributeSet`() {
        val attrs = Robolectric.buildAttributeSet()
                .build()
        val view = EmeraldLabel(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun `Should set text`() {
        val testValue = "test"
        val expectedValue = "TEST"
        label.text = testValue
        assertEquals(expectedValue, label.text)
        assertEquals(expectedValue, label.emeraldLabelText.text)
    }

    @Test
    fun `Should set properties`() {
        val typeMock = mock(EmeraldLabelType::class.java)
        val stateMock = mock(LabelStateHandler::class.java)
        val sizeMock = mock(LabelSizeHandler::class.java)
        val marginMock = mock(LabelMarginHandler::class.java)
        val shapeMock = mock(LabelShapeHandler::class.java)
        val colorResource = android.R.color.black
        `when`(typeMock.color).thenReturn(colorResource)

        label.setProperties(typeMock, stateMock, sizeMock, shapeMock, marginMock)

        verify(typeMock).color
        verify(stateMock).setProperties(label,
                ContextCompat.getColor(ApplicationProvider.getApplicationContext(), colorResource))
        verify(sizeMock).setDimensions(label.emeraldLabelText)
        verify(marginMock).setMargin(label.emeraldLabelText)
        verify(shapeMock).setBackground(label)
    }

    @Test
    fun `Should set custom color`() {
        val customColor = android.R.color.black
        val stateMock = mock(LabelStateHandler::class.java)

        label.setCustomColor(stateMock, customColor)

        verify(stateMock).setProperties(label,
                ContextCompat.getColor(ApplicationProvider.getApplicationContext(), customColor))
    }

    @Test(expected = Resources.NotFoundException::class)
    fun `Should not set properties with invalid resource`() {
        val typeMock = mock(EmeraldLabelType::class.java)
        val stateMock = mock(LabelStateHandler::class.java)
        val sizeMock = mock(LabelSizeHandler::class.java)

        label.setProperties(typeMock, stateMock, sizeMock)
    }

    @Test
    fun `Should set icon in start`() {
        val iconResource = R.drawable.ic_add_circle_white_24dp
        label.setIcon(iconResource)
        assertEquals(View.VISIBLE, label.emeraldLabelImage.visibility)
        assertEquals(View.GONE, label.emeraldLabelImageEnd.visibility)
    }

    @Test
    fun `Should set icon in end`() {
        val iconResource = R.drawable.ic_add_circle_white_24dp
        label.setIcon(iconResource, EmeraldLabelIconPosition.END)
        assertEquals(View.GONE, label.emeraldLabelImage.visibility)
        assertEquals(View.VISIBLE, label.emeraldLabelImageEnd.visibility)
    }

    @Test
    fun `Should set icon as drawable`() {
        val icon = context.getDrawable(R.drawable.ic_add_circle_white_24dp)
        label.setIcon(icon, EmeraldLabelIconPosition.END)
        assertEquals(View.VISIBLE, label.emeraldLabelImageEnd.visibility)
    }

    @Test
    fun `Should hide icons`() {
        label.removeIcons()
        assertEquals(View.GONE, label.emeraldLabelImage.visibility)
        assertEquals(View.GONE, label.emeraldLabelImageEnd.visibility)
    }
}