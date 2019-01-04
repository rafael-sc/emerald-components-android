package br.com.stone.emeraldcomponents.basics.spinner

import android.widget.AdapterView
import br.com.stone.emeraldcomponents.basic.spinner.EmeraldSpinner
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.any
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 19/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldSpinnerTest {

    private lateinit var spinner: EmeraldSpinner

    @Before
    fun setup() {
        spinner = EmeraldSpinner(RuntimeEnvironment.application)
    }

    @Test
    fun testSetItemsWithoutPlaceHolder() {
        val testList = listOf(
                "Description A",
                "Description B",
                "Description C",
                "Description D",
                "Description E"
        )
        spinner.setItems(testList)
        Assert.assertEquals(testList.size, spinner.adapter.count)
        Assert.assertEquals(testList.first(), spinner.adapter.getItem(0))
    }

    @Test
    fun testSetItemsWithPlaceHolder() {
        val placeholder = "placeholder"
        val testList = listOf("Description A", "Description B")
        spinner.placeholder = placeholder
        spinner.setItems(testList)
        Assert.assertEquals(testList.size + 1, spinner.adapter.count)
        Assert.assertEquals(placeholder, spinner.adapter.getItem(0))
        Assert.assertEquals(testList.first(), spinner.adapter.getItem(1))
    }

    @Test
    fun testSetOnItemSelectedListenerWithoutPlaceHolder() {
        val listener = mock(AdapterView.OnItemSelectedListener::class.java)
        spinner.onItemSelectedListener = listener

        val testList = listOf("Description A", "Description B")
        spinner.setItems(testList)

        spinner.setSelection(0)
        spinner.setSelection(1)
        verify(listener, times(2)).onItemSelected(any(), any(), anyInt(), anyLong())
    }

    @Test
    fun testSetOnItemSelectedListenerWithPlaceHolder() {
        val listener = mock(AdapterView.OnItemSelectedListener::class.java)
        spinner.onItemSelectedListener = listener

        spinner.placeholder = "placeholder"

        val testList = listOf("Description A")
        spinner.setItems(testList)

        spinner.setSelection(0)
        verify(listener, times(0)).onItemSelected(any(), any(), anyInt(), anyLong())

        spinner.setSelection(1)
        verify(listener).onItemSelected(any(), any(), anyInt(), anyLong())
    }
}