package br.com.stone.emeraldcomponents.basics.input

import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.input.EmeraldEditText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 18/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */

@RunWith(RobolectricTestRunner::class)
class EmeraldEditTextTest {

    private lateinit var view: EmeraldEditText

    private lateinit var activity: FragmentActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).get()
        activity.setTheme(R.style.Emerald)
        view = EmeraldEditText(activity)
    }

    @Test
    fun testNotNull() {
        assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val emeraldAutoCompleteTextView = EmeraldEditText(activity,
                Mockito.mock(AttributeSet::class.java))
        assertNotNull(emeraldAutoCompleteTextView)
    }

    @Test
    fun testIsValidTrue() {
        view.editText?.setText("test")
        assertTrue(view.isValid())
    }

    @Test
    fun testIsValidFalse() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.maskType, "phoneNumber")
                .build()

        val view = EmeraldEditText(activity, attrs)
        view.text = "test"
        assertFalse(view.isValid())
    }

    @Test
    fun testIsValidMaskFalse() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.mask, "([00]) [0000]-[00009]")
                .build()

        val view = EmeraldEditText(activity, attrs)
        view.text = "2121"
        assertFalse(view.isValid())
    }

    @Test
    fun testIsValidMaskTrue() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.mask, "([00]) [0000]-[00009]")
                .build()

        val view = EmeraldEditText(activity, attrs)
        view.text = "2121212121"
        assertTrue(view.isValid())
    }

    @Test
    fun testTextIsFilled() {
        val test = "test"
        view.text = test
        assertEquals(test, view.editText?.text.toString())
    }

    @Test
    fun testRequiredField() {
        view.required = true
        view.text = ""
        assertFalse(view.isValid())
    }

    @Test
    fun testNotRequiredField() {
        view.required = false
        view.text = ""
        assertTrue(view.isValid())
    }

    @Test
    fun testOnEditActionDoneMethod() {
        var x = 0
        view.editText?.imeOptions = EditorInfo.IME_ACTION_DONE
        view.setOnEditorActionDone { x = 1 }
        view.editText?.onEditorAction(EditorInfo.IME_ACTION_DONE)
        assertEquals(1, x)
    }
}