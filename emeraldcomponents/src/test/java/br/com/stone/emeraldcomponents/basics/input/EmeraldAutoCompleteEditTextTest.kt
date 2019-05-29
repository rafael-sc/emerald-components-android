package br.com.stone.emeraldcomponents.basics.input

import android.support.v4.app.FragmentActivity
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.input.EmeraldAutoCompleteEditText
import kotlinx.android.synthetic.main.widget_autocomplete.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 13/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldAutoCompleteEditTextTest {

    private lateinit var view: EmeraldAutoCompleteEditText

    private lateinit var activity: FragmentActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).get()
        activity.setTheme(R.style.Emerald)
        view = EmeraldAutoCompleteEditText(activity)
    }

    @Test
    fun testNotNull() {
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val emeraldAutoCompleteTextView = EmeraldAutoCompleteEditText(activity,
                Robolectric.buildAttributeSet().build())
        Assert.assertNotNull(emeraldAutoCompleteTextView)
    }

    @Test
    fun testSetItems() {
        val testList = setOf(
                ObjectTest("101", "Description A"),
                ObjectTest("102", "Description B"),
                ObjectTest("103", "Description C"),
                ObjectTest("004", "Description D"),
                ObjectTest("005", "Description E")
        )
        view.setItems(testList, {})
        assertEquals(testList.size, view.emeraldAutoCompleteView.adapter.count)
        assertEquals(testList.first(), view.emeraldAutoCompleteView.adapter.getItem(0))

        val testList1 = setOf("Description B", "Description C", "Description D", "Description E")
        view.setItems(testList1, {})
        assertEquals(testList1.size, view.emeraldAutoCompleteView.adapter.count)
        assertEquals(testList1.first(), view.emeraldAutoCompleteView.adapter.getItem(0))
    }

    @Test
    fun testIsValid() {
        val testList = setOf(
                ObjectTest("101", "Description A"),
                ObjectTest("005", "Description E")
        )
        view.setItems(testList, {})
        view.emeraldAutoCompleteView.setText(testList.toList()[0].toString())
        assertTrue(view.isValid())

        view.emeraldAutoCompleteView.setText("Anything")
        assertFalse(view.isValid())
    }

    class ObjectTest(private val anyName: String, private val otherName: String) {
        override fun toString(): String {
            return "$anyName - $otherName"
        }
    }
}