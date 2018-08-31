package br.com.stone.emeraldcomponents.basics.input

import android.support.design.widget.TextInputLayout
import android.support.v4.app.FragmentActivity
import android.widget.EditText
import android.widget.TextView
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.input.EditTextState
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 18/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EditTextStateTest {

    @Test
    fun testSetStateWithEditText() {
        val editText = EditText(RuntimeEnvironment.application)
        val messageView = TextView(RuntimeEnvironment.application)
        val editTextState = EditTextState(editText, messageView)
        val testState = EditTextState.InputState.SUCCESS
        editTextState.setState(testState)
        assertEquals(editTextState.state, testState)
    }

    @Test
    fun testSetStateWithTextInputLayout() {
        val activity = Robolectric.buildActivity(FragmentActivity::class.java).get()
        activity.setTheme(R.style.Emerald)
        val textInputLayout = TextInputLayout(activity)
        textInputLayout.addView(EditText(activity))
        val editTextState = EditTextState(textInputLayout)
        val testState = EditTextState.InputState.SUCCESS
        editTextState.setState(testState)
        assertEquals(editTextState.state, testState)
    }
}