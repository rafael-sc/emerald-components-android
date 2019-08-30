package br.com.stone.emeraldcomponents.basic.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlin.math.absoluteValue

/**
 * Created by victor.cruz on 28/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
class AutoFillTextWatcher(val editText: EditText,
                          private val autoFillSequence: Char,
                          private val autoFillLength: Int,
                          private val valueListener: (unmaskedText: String) -> Unit = {}) : TextWatcher {

    override fun afterTextChanged(text: Editable?) {
        editText.removeTextChangedListener(this)

        var aux = text.toString()
        val difference = autoFillLength - aux.length
        if (difference > 0) {
            for (i in 1..difference) {
                aux = autoFillSequence + aux
            }
        } else if (difference < 0) {
            for (i in 1..difference.absoluteValue) {
                aux = aux.substring(1)
            }
        }

        editText.setText(aux)
        editText.setSelection(aux.length)
        editText.addTextChangedListener(this)

        valueListener(removeAutoFillMask(text.toString()))
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    private fun removeAutoFillMask(text: String): String {
        return text.apply { while (startsWith(autoFillSequence)) replaceRange(0..0, "") }
    }
}