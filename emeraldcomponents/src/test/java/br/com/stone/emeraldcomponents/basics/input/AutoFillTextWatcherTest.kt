package br.com.stone.emeraldcomponents.basics.input

import android.text.SpannableStringBuilder
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.input.AutoFillTextWatcher
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
class AutoFillTextWatcherTest {

    private lateinit var textWatcher: AutoFillTextWatcher

    private lateinit var editText: EditText

    @Before
    fun setup() {
        editText = EditText(ApplicationProvider.getApplicationContext())
        textWatcher = AutoFillTextWatcher(editText, '0', 4)
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
    fun `Should remove chars from the beginning when input is bigger than length limit`() {
        textWatcher.afterTextChanged(SpannableStringBuilder("12345"))
        assertEquals("2345", editText.text.toString())
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