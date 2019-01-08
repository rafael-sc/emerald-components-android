package br.com.stone.emeraldcomponents.basics.spinner

import android.graphics.Color
import android.widget.TextView
import br.com.stone.emeraldcomponents.basic.spinner.EmeraldSpinnerArrayAdapter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by victor.cruz on 04/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldSpinnerArrayAdapterTest {

    private lateinit var adapter: EmeraldSpinnerArrayAdapter
    private val testList = listOf("Description A", "Description B")

    @Before
    fun setup() {
        adapter = EmeraldSpinnerArrayAdapter(RuntimeEnvironment.application, android.R.layout.simple_dropdown_item_1line, testList)
    }

    @Test
    fun testIsEnabledCasePosition0() {
        assertFalse(adapter.isEnabled(0))
    }

    @Test
    fun testIsEnabledCasePosition1() {
        assertTrue(adapter.isEnabled(1))
    }

    @Test
    fun testGetDropDownViewCasePositionZero() {
        val textView = adapter.getDropDownView(0, null, null) as TextView
        assertEquals(textView.textColors.defaultColor, Color.GRAY)
    }

    @Test
    fun testGetDropDownViewCasePositionOne() {
        val textView = adapter.getDropDownView(1, null, null) as TextView
        assertEquals(textView.textColors.defaultColor, Color.BLACK)
    }

    @Test
    fun testGetViewCasePositionZero() {
        val textView = adapter.getView(0, null, null) as TextView
        assertEquals(textView.textColors.defaultColor, Color.GRAY)
    }

    @Test
    fun testGetViewCasePositionOne() {
        val textView = adapter.getView(1, null, null) as TextView
        assertEquals(textView.textColors.defaultColor, Color.BLACK)
    }
}