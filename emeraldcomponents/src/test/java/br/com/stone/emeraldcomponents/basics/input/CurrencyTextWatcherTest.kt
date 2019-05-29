package br.com.stone.emeraldcomponents.basics.input

import android.text.SpannableStringBuilder
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.input.CurrencyTextWatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
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
        editText = EditText(ApplicationProvider.getApplicationContext())
        textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
    }

    @Test
    fun `Should format value`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("100"))
        assertEquals("R$ 1,00", editText.text.toString())
    }

    @Test
    fun `Should handle empty text`() {
        textWatcher.afterTextChanged(SpannableStringBuilder(""))
        assertEquals("R$ 0,00", editText.text.toString())
    }

    @Test
    fun `Should handle word values`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("invalid"))
        assertEquals("R$ 0,00", editText.text.toString())
    }

    @Test
    fun `Should handle big values`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("111111111111111111"))
        assertEquals("R$ 111.111.111.111,11", editText.text.toString())
    }

    @Test
    fun `Should beforeTextChanged be called`() {
        textWatcher.beforeTextChanged("1", 0, 0, 0)
    }

    @Test
    fun `Should onTextChanged be called`() {
        textWatcher.onTextChanged("1", 0, 0, 0)
    }
}