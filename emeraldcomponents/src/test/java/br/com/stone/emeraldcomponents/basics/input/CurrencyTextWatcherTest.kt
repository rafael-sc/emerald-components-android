package br.com.stone.emeraldcomponents.basics.input

import android.text.SpannableStringBuilder
import android.widget.EditText
import br.com.stone.emeraldcomponents.basic.input.CurrencyTextWatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.Locale

/**
 * Created by renan.silva on 17/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class CurrencyTextWatcherTest {

    private lateinit var textWatcher: CurrencyTextWatcher

    private lateinit var editText: EditText

    @Before
    fun setup() {
        editText = EditText(RuntimeEnvironment.application)
        textWatcher = CurrencyTextWatcher(editText)
    }

    @Test
    fun testLocaleBrazilCurrencyFormat() {
        val textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
        textWatcher.afterTextChanged(SpannableStringBuilder("100"))
        assertEquals("R$ 1,00", editText.text.toString())
    }

    @Test
    fun testLocaleBrazilCurrencyInvalidFormat() {
        val textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
        textWatcher.afterTextChanged(SpannableStringBuilder(""))
        assertEquals("R$ 0,00", editText.text.toString())
    }

    @Test
    fun testAfterTextChanged() {
        textWatcher.afterTextChanged(SpannableStringBuilder("1"))
        assertEquals('1', editText.text.last())
    }

    @Test
    fun testAfterTextChangedWithMaximumLength() {
        textWatcher.afterTextChanged(SpannableStringBuilder("111111111111111111"))
        assertEquals('1', editText.text.last())
    }

    @Test
    fun testBeforeTextChanged() {
        textWatcher.beforeTextChanged("", 0, 0, 0)
    }

    @Test
    fun testOnTextChanged() {
        textWatcher.onTextChanged("", 0, 0, 0)
    }
}