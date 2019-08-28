package br.com.stone.emeraldcomponents.basic.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * Created by victor.cruz on 28/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
class AutoFillTextWatcher(val editText: EditText,
                          private val autoFillSequence: CharSequence,
                          private val autoFillLength: Int,
                          private val valueListener: (unmaskedText: String) -> Unit = {}) : TextWatcher {

    override fun afterTextChanged(text: Editable?) {
        editText.removeTextChangedListener(this)

        val aux = text
        val lengthDifference = (aux?.length ?: 0) - autoFillLength
        if (lengthDifference < 0) {
            for (i in 1..lengthDifference.absoluteValue) {
                aux?.insert(0, autoFillSequence)
            }
        } else if (lengthDifference > 0) {
            for (i in 1..lengthDifference) {
                aux?.replace(0, 1, "")
            }
        }

        editText.text = aux
        editText.setSelection(aux?.length ?: 0)
        editText.addTextChangedListener(this)

        valueListener(removeAutoFillMask(aux.toString()))
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    private fun removeAutoFillMask(text: String): String {
        return text.apply { while (startsWith(autoFillSequence)) removePrefix(autoFillSequence) }
    }
}