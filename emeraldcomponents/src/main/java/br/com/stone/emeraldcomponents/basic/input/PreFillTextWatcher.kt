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
class PreFillTextWatcher(val editText: EditText,
                         private val preFillChar: Char,
                         private val preFillLength: Int,
                         private val valueListener: (unmaskedText: String) -> Unit = {}) : TextWatcher {

    var fullString: String? = null

    override fun afterTextChanged(text: Editable?) {
        editText.removeTextChangedListener(this)

        var str = text.toString()
        if (str.firstOrNull() != preFillChar && str.length > preFillLength) {
            str = fullString ?: str.substring(0, preFillLength)
        } else {
            val difference = preFillLength - str.length
            if (difference > 0) {
                for (i in 1..difference) {
                    str = preFillChar + str
                }
            } else if (difference < 0) {
                for (i in 1..difference.absoluteValue) {
                    str = str.substring(1)
                }
                fullString = str
            }
        }

        editText.setText(str)
        editText.setSelection(str.length)
        editText.addTextChangedListener(this)

        valueListener(str)
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}
}