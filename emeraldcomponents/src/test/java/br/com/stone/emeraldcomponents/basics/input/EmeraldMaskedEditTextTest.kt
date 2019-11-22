package br.com.stone.emeraldcomponents.basics.input

import android.content.Context
import android.content.res.Configuration
import androidx.fragment.app.FragmentActivity
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText.MaskTypes.PRE_FILL
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
class EmeraldMaskedEditTextTest {

    lateinit var view: EmeraldMaskedEditText

    lateinit var defaultErrorMessage: String

    @Before
    fun setup() {
        view = EmeraldMaskedEditText(ApplicationProvider.getApplicationContext())
        defaultErrorMessage = ApplicationProvider.getApplicationContext<Context>()
                .getString(R.string.emerald_mask_error)
    }

    @Test
    fun `Should create view`() {
        val view = EmeraldMaskedEditText(ApplicationProvider.getApplicationContext())
        assertNotNull(view)
    }

    @Test
    fun `Should create view with attribute set`() {
        val view = EmeraldMaskedEditText(ApplicationProvider.getApplicationContext(),
                Robolectric.buildAttributeSet().build())
        assertNotNull(view)
    }

    @Test
    fun `Should define mask`() {
        val mask = "A"
        view.defineMask(mask)
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint by phonne number mask`() {
        val mask = "(00) 0000-0000"
        view.type = EmeraldMaskedEditText.MaskTypes.PHONENUMBER
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint by cell phone mask`() {
        val mask = "(00) 00000-0000"
        view.type = EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint by cpf mask`() {
        val mask = "000.000.000-00"
        view.type = EmeraldMaskedEditText.MaskTypes.CPF
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint cnpj mask`() {
        val mask = "00.000.000/0000-00"
        view.type = EmeraldMaskedEditText.MaskTypes.CNPJ
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint by cep mask`() {
        val mask = "00000-000"
        view.type = EmeraldMaskedEditText.MaskTypes.CEP
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should define hint by text mask`() {
        val mask = ""
        view.type = EmeraldMaskedEditText.MaskTypes.TEXT
        assertEquals(mask, view.hint)
    }

    @Test
    fun `Should be valid by phone number`() {
        view.type = EmeraldMaskedEditText.MaskTypes.PHONENUMBER

        view.setText("(11) 1111-1111")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("(11) 1111-111")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by cell phone`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER

        view.setText("(11) 1111-11112")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("(11) 1111-1111")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by email`() {
        view.type = EmeraldMaskedEditText.MaskTypes.EMAIL

        view.setText("email@mail.com")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("email")
        view.onFocusChangeListener.onFocusChange(view, false)

        val context = ApplicationProvider.getApplicationContext<Context>()
        assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        assertFalse(view.isValid())

        view.setText("email@email")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        assertFalse(view.isValid())

        view.setText("email@email.")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        assertFalse(view.isValid())

        view.setText("@email.com")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        assertFalse(view.isValid())

        view.setText("email@.com")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by cpf`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CPF

        view.setText("466.887.084-69")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("518.461.001-2")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("518.461.01-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("518.41.001-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("18.461.001-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by cnpj`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CNPJ

        view.setText("27.146.359/0001-28")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("56.614.532/0001-7")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("56.614.532/01-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("56.614.52/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("56.64.532/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("5.614.532/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by cep`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CEP

        view.setText("22041-080")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("22041-08")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())

        view.setText("2204-080")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by text`() {
        view.type = EmeraldMaskedEditText.MaskTypes.TEXT

        view.setText("test")
        assertNull(view.error)
        assertTrue(view.isValid())

        view.setText("")
        view.onFocusChangeListener.onFocusChange(view, false)
        assertEquals(view.errorMessage, defaultErrorMessage)
        assertFalse(view.isValid())
    }

    @Test
    fun `Should be valid by pre fill`() {
        view.type = PRE_FILL

        view.setText("test")
        assertNull(view.error)
        assertTrue(view.isValid())
    }

    @Test
    fun `Should define mask by currency`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CURRENCY
        view.setText("123")
        assertEquals('3', view.text?.last())
    }

    @Test
    fun `Should define mask by pre fill`() {
        view.type = PRE_FILL
        assertEquals(view.type, PRE_FILL)
    }

    @Test
    fun `Should use default value when it is not set by the user`() {
        view.type = PRE_FILL

        view.setText("")

        assertEquals("0", view.text.toString())
    }

    @Test
    fun `Should set mask`() {
        view.type = EmeraldMaskedEditText.MaskTypes.NONE
        assertEquals(EmeraldMaskedEditText.MaskTypes.NONE, view.type)
    }

    @Test
    fun `Should set unmasked text by currency`() {
        view.type = EmeraldMaskedEditText.MaskTypes.CURRENCY
        view.setText("123.12")
        assertEquals("123.12", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text and locale by currency`() {
        val locale = Locale.US
        Locale.setDefault(locale)

        val activity = Robolectric.buildActivity(FragmentActivity::class.java).get()
        activity.setTheme(R.style.Emerald)

        val configuration: Configuration = activity.resources.configuration
        configuration.setLocale(locale)

        view.type = (EmeraldMaskedEditText.MaskTypes.CURRENCY)
        view.setText("123.12")
        assertEquals("$123.12", view.text.toString())
        assertEquals("123.12", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by phone number`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.PHONENUMBER)
        view.setText("8499999999")
        assertEquals("(84) 9999-9999", view.text.toString())
        assertEquals("8499999999", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by cell phone`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER)
        view.setText("84999999999")
        assertEquals("(84) 99999-9999", view.text.toString())
        assertEquals("84999999999", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by cep`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CEP)
        view.setText("21000000")
        assertEquals("21000-000", view.text.toString())
        assertEquals("21000000", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by cnpj`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CNPJ)
        view.setText("00000000000000")
        assertEquals("00.000.000/0000-00", view.text.toString())
        assertEquals("00000000000000", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by cpf`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CPF)
        view.setText("00000000000")
        assertEquals("000.000.000-00", view.text.toString())
        assertEquals("00000000000", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by email`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.EMAIL)
        view.setText("email@email.com")
        assertEquals("email@email.com", view.text.toString())
        assertEquals("", view.unmaskedText)
    }

    @Test
    fun `Should set unmasked text by none`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.NONE)
        val text = "Teste teste de teste"
        view.setText(text)
        assertEquals(text, view.text.toString())
        assertEquals("", view.unmaskedText)
    }

    @Test
    fun `Should be valid credit card`() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CREDIT_CARD)
        val text = "1111222233334444"
        view.setText(text)
        assertEquals("1111 2222 3333 4444", view.text.toString())
        assertEquals("1111222233334444", view.unmaskedText)
    }

    @Test
    fun `Should remove old text watchers when a new one is selected`() {
        view.run {
            fillLength = 1
            fillSequence = '0'
            type = PRE_FILL
        }

        view.run {
            fillLength = 3
            fillSequence = 'a'
            type = PRE_FILL
        }

        view.setText("23")
        assertEquals("a23", view.text.toString())
    }
}