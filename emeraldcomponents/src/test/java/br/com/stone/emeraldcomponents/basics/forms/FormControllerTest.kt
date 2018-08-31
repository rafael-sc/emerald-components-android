package br.com.stone.emeraldcomponents.basics.forms

import android.app.Application
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import br.com.stone.emeraldcomponents.basic.forms.FormController
import br.com.stone.emeraldcomponents.basic.input.SelfValidatorField
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 04/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class FormControllerTest {

    private class TestValidatorField(context: Context?, val valid: Boolean) : View(context), SelfValidatorField {
        override fun isValid(): Boolean {
            return valid
        }
    }

    private lateinit var context: Application

    private lateinit var parentView: ViewGroup

    @Before
    fun setup() {
        context = RuntimeEnvironment.application
        parentView = LinearLayout(context)
    }

    @Test
    fun testValidateFieldsTrue() {
        parentView.addView(EditText(context))
        parentView.addView(TestValidatorField(context, true))
        assertTrue(FormController.validateFields(parentView))
    }

    @Test
    fun testValidateFieldsFalse() {
        parentView.addView(EditText(context))
        parentView.addView(TestValidatorField(context, false))
        assertFalse(FormController.validateFields(parentView))
    }

    @Test
    fun testValidateFieldsWithoutSelfValidatorField() {
        parentView.addView(EditText(context))
        assertTrue(FormController.validateFields(parentView))
    }

    @Test
    fun testValidateFieldsNested() {
        parentView.addView(FrameLayout(context).apply { addView(EditText(context)) })
        parentView.addView(LinearLayout(context).apply {
            addView(EditText(context))
            addView(TestValidatorField(context, false))
        })
        assertFalse(FormController.validateFields(parentView))
    }
}