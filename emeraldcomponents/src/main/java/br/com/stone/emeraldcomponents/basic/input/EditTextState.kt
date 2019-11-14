package br.com.stone.emeraldcomponents.basic.input

import android.content.res.ColorStateList
import com.google.android.material.textfield.TextInputLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import android.widget.EditText
import android.widget.TextView
import br.com.stone.emeraldcomponents.R

/**
 * Created by renan.silva on 18/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EditTextState {

    enum class InputState(val color: Int,
                          val drawable: Int = 0,
                          val textAppearance: Int = R.style.Emerald_TextAppearance) {
        SUCCESS(R.color.emerald_input_success, R.drawable.ic_check_circle_success_24dp,
                R.style.Emerald_SuccessAppearance),
        WARNING(R.color.emerald_input_warning, R.drawable.ic_warning_24dp,
                R.style.Emerald_WarningAppearance),
        ERROR(R.color.emerald_input_error, R.drawable.ic_close_circle,
                R.style.Emerald_ErrorAppearance),
        FOCUS(R.color.emerald_input_focused),
        DISABLED(R.color.emerald_input_disabled),
        DEFAULT(R.color.emerald_input_default)
    }

    lateinit var state: InputState private set
    private var editText: Pair<EditText, TextView>? = null
    private var textInput: TextInputLayout? = null

    constructor(editText: EditText, messageView: TextView) {
        this.editText = Pair(editText, messageView)
    }

    constructor(textInput: TextInputLayout) {
        this.textInput = textInput
    }

    fun setState(state: InputState, message: String = "") {
        if (textInput != null) setState(textInput!!, state, message)
        else setState(editText!!, state, message)

        textInput?.isEnabled = state != InputState.DISABLED
        editText?.first?.isEnabled = state != InputState.DISABLED
    }

    private fun setState(textInput: TextInputLayout, state: InputState, message: String) {
        this.state = state
        textInput.run {
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, state.color))
            editText?.let {
                ViewCompat.setBackgroundTintList(it, colorStateList)
                if (this.errorIconDrawable != null) it.setCompoundDrawablesWithIntrinsicBounds(0, 0, state.drawable, 0)
            }

            if (message.isBlank()) {
                isErrorEnabled = false
            } else {
                setErrorTextAppearance(state.textAppearance)
                error = message
            }

            refreshDrawableState()
        }
    }

    private fun setState(editText: Pair<EditText, TextView>, state: InputState, message: String) {
        this.state = state
        editText.run {
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(first.context, state.color))
            ViewCompat.setBackgroundTintList(first, colorStateList)
            first.setCompoundDrawablesWithIntrinsicBounds(0, 0, state.drawable, 0)
            first.refreshDrawableState()

            second.text = message
            second.setTextColor(state.color)
        }
    }
}