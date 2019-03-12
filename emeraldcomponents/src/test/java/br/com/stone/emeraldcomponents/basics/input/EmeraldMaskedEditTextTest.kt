package br.com.stone.emeraldcomponents.basics.input

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.fragment.app.FragmentActivity
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
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
    fun testInstanceWithContext() {
        val view = EmeraldMaskedEditText(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val view = EmeraldMaskedEditText(ApplicationProvider.getApplicationContext(), mock(AttributeSet::class.java))
        Assert.assertNotNull(view)
    }

    @Test
    fun testDefineMask() {
        val mask = "A"
        view.defineMask(mask)
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypePhone() {
        val mask = "(00) 0000-0000"
        view.type = EmeraldMaskedEditText.MaskTypes.PHONENUMBER
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypeCellPhone() {
        val mask = "(00) 00000-0000"
        view.type = EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypeCpf() {
        val mask = "000.000.000-00"
        view.type = EmeraldMaskedEditText.MaskTypes.CPF
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypeCnpj() {
        val mask = "00.000.000/0000-00"
        view.type = EmeraldMaskedEditText.MaskTypes.CNPJ
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypeCep() {
        val mask = "00000-000"
        view.type = EmeraldMaskedEditText.MaskTypes.CEP
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testDefineMaskByTypeText() {
        val mask = ""
        view.type = EmeraldMaskedEditText.MaskTypes.TEXT
        Assert.assertEquals(mask, view.hint)
    }

    @Test
    fun testIsValidByTypePhone() {
        view.type = EmeraldMaskedEditText.MaskTypes.PHONENUMBER

        view.setText("(11) 1111-1111")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("(11) 1111-111")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeCellPhone() {
        view.type = EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER

        view.setText("(11) 1111-11112")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("(11) 1111-1111")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeEmail() {
        view.type = EmeraldMaskedEditText.MaskTypes.EMAIL

        view.setText("email@mail.com")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("email")
        view.onFocusChangeListener.onFocusChange(view, false)

        val context = ApplicationProvider.getApplicationContext<Context>()
        Assert.assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        Assert.assertFalse(view.isValid())

        view.setText("email@email")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        Assert.assertFalse(view.isValid())

        view.setText("email@email.")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        Assert.assertFalse(view.isValid())

        view.setText("@email.com")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        Assert.assertFalse(view.isValid())

        view.setText("email@.com")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, context.getString(R.string.emerald_invalid_email))
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeCpf() {
        view.type = EmeraldMaskedEditText.MaskTypes.CPF

        view.setText("466.887.084-69")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("518.461.001-2")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("518.461.01-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("518.41.001-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("18.461.001-42")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeCnpj() {
        view.type = EmeraldMaskedEditText.MaskTypes.CNPJ

        view.setText("27.146.359/0001-28")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("56.614.532/0001-7")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("56.614.532/01-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("56.614.52/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("56.64.532/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("5.614.532/001-73")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeCep() {
        view.type = EmeraldMaskedEditText.MaskTypes.CEP

        view.setText("22041-080")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("22041-08")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())

        view.setText("2204-080")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testIsValidByTypeText() {
        view.type = EmeraldMaskedEditText.MaskTypes.TEXT

        view.setText("test")
        Assert.assertNull(view.error)
        Assert.assertTrue(view.isValid())

        view.setText("")
        view.onFocusChangeListener.onFocusChange(view, false)
        Assert.assertEquals(view.errorMessage, defaultErrorMessage)
        Assert.assertFalse(view.isValid())
    }

    @Test
    fun testDefineMaskByTypeCurrency() {
        view.type = EmeraldMaskedEditText.MaskTypes.CURRENCY
        view.setText("123")
        assertEquals('3', view.text?.last())
    }

    @Test
    fun testType() {
        view.type = EmeraldMaskedEditText.MaskTypes.NONE
        Assert.assertEquals(EmeraldMaskedEditText.MaskTypes.NONE, view.type)
    }

    @Test
    fun testUnmaskedCurrencyDefaultLocale() {
        view.type = EmeraldMaskedEditText.MaskTypes.CURRENCY
        view.setText("123.12")
        assertEquals("123.12", view.unmaskedText)
    }

    @Test
    fun testUnmaskedCurrencyCustomLocale() {
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
    fun testUnmaskedPhone() {
        view.type = (EmeraldMaskedEditText.MaskTypes.PHONENUMBER)
        view.setText("8499999999")
        assertEquals("(84) 9999-9999", view.text.toString())
        assertEquals("8499999999", view.unmaskedText)
    }

    @Test
    fun testUnmaskedCelPhone() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CELLPHONENUMBER)
        view.setText("84999999999")
        assertEquals("(84) 99999-9999", view.text.toString())
        assertEquals("84999999999", view.unmaskedText)
    }

    @Test
    fun testUnmaskedCep() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CEP)
        view.setText("21000000")
        assertEquals("21000-000", view.text.toString())
        assertEquals("21000000", view.unmaskedText)
    }

    @Test
    fun testUnmaskedCnpj() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CNPJ)
        view.setText("00000000000000")
        assertEquals("00.000.000/0000-00", view.text.toString())
        assertEquals("00000000000000", view.unmaskedText)
    }

    @Test
    fun testUnmaskedCpf() {
        view.type = (EmeraldMaskedEditText.MaskTypes.CPF)
        view.setText("00000000000")
        assertEquals("000.000.000-00", view.text.toString())
        assertEquals("00000000000", view.unmaskedText)
    }

    @Test
    fun testUnmaskedEmail() {
        view.type = (EmeraldMaskedEditText.MaskTypes.EMAIL)
        view.setText("email@email.com")
        assertEquals("email@email.com", view.text.toString())
        assertEquals("", view.unmaskedText)
    }

    @Test
    fun testUnmaskedNone() {
        view.type = (EmeraldMaskedEditText.MaskTypes.NONE)
        val text = "Teste teste de teste"
        view.setText(text)
        assertEquals(text, view.text.toString())
        assertEquals("", view.unmaskedText)
    }
}