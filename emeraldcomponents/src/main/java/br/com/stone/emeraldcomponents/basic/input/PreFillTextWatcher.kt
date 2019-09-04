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

    private var fullString: String? = null

    override fun afterTextChanged(text: Editable?) {
        editText.removeTextChangedListener(this)

        var str = text.toString()
        if (str.firstOrNull() != preFillChar && str.length > preFillLength) {
            str = fullString ?: str.substring(0, preFillLength)
        } else {
            val difference = preFillLength - str.length
            if (difference > 0) {
                str = str.padStart(preFillLength, preFillChar)
            } else if (difference < 0) {
                str = str.substring(difference.absoluteValue)
                fullString = str
            }
        }

        editText.setText(str)
        editText.setSelection(editText.text.length)
        editText.addTextChangedListener(this)

        valueListener(str)
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}
}