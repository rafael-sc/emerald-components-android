package br.com.stone.emeraldcomponents.basics.input

import android.text.SpannableStringBuilder
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.input.PreFillTextWatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by victor.cruz on 30/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class PreFillTextWatcherTest {

    private lateinit var textWatcher: PreFillTextWatcher

    private lateinit var editText: EditText

    @Before
    fun setup() {
        editText = EditText(ApplicationProvider.getApplicationContext())
        textWatcher = PreFillTextWatcher(editText, '0', 4)
    }

    @Test
    fun `Should fill when input`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("1"))
        assertEquals("0001", editText.text.toString())
    }

    @Test
    fun `Should fill when empty`() {
        textWatcher.afterTextChanged(SpannableStringBuilder(""))
        assertEquals("0000", editText.text.toString())
    }

    @Test
    fun `Should remove chars from the end when input is bigger than length limit`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("12345"))
        assertEquals("1234", editText.text.toString())
    }

    @Test
    fun `Should remove chars from the beginning when input is bigger and starts with fill char`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("00345"))
        assertEquals("0345", editText.text.toString())
    }

    @Test
    fun `Should not include character after text is full`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("01234"))
        textWatcher.afterTextChanged(SpannableStringBuilder("56789"))
        assertEquals("1234", editText.text.toString())
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