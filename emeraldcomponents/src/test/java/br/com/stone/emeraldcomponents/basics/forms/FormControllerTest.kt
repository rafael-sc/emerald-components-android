package br.com.stone.emeraldcomponents.basics.forms

import android.app.Application
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.forms.FormController
import br.com.stone.emeraldcomponents.basic.input.EmeraldEditText
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText
import br.com.stone.emeraldcomponents.basic.input.SelfValidatorField
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

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
        context = ApplicationProvider.getApplicationContext()
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

    @Test
    fun testMultipleFields() {
        val activity = Robolectric.buildActivity(FragmentActivity::class.java).get()
        activity.setTheme(R.style.Emerald)

        val cepMask = EmeraldEditText(activity)
        cepMask.setMaskType(EmeraldMaskedEditText.MaskTypes.CEP)
        cepMask.text = "13"
        val phoneMask = EmeraldEditText(activity)
        phoneMask.setMaskType(EmeraldMaskedEditText.MaskTypes.PHONENUMBER)
        phoneMask.text = "test"
        val requiredMask = EmeraldEditText(activity)
        requiredMask.required = true

        parentView.addView(cepMask)
        parentView.addView(phoneMask)
        parentView.addView(requiredMask)

        assertFalse(FormController.validateFields(parentView))

        assertEquals(activity.getString(R.string.emerald_mask_error), (parentView.getChildAt(0) as EmeraldEditText).error)
        assertEquals(activity.getString(R.string.emerald_mask_error), (parentView.getChildAt(1) as EmeraldEditText).error)
        assertEquals(activity.getString(R.string.emerald_empty_field), (parentView.getChildAt(2) as EmeraldEditText).error)
    }
}