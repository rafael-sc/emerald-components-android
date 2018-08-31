package br.com.stone.emeraldcomponents.basic.input

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import br.com.stone.emeraldcomponents.R

/**
 * Created by renan.silva on 20/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
abstract class EmeraldBaseEditText : TextInputLayout, SelfValidatorField {

    private val textWatcher: TextWatcher
    protected var inputType: Int = EditorInfo.TYPE_CLASS_TEXT

    protected var textAttribute: String = ""

    var required = false
    val state = EditTextState(this)

    var text: String = ""
        get() = editText?.text.toString()
        set(value) {
            field = value
            editText?.setText(field)
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    init {
        state.setState(EditTextState.InputState.DEFAULT)
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                state.setState(EditTextState.InputState.FOCUS)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    private fun setAttributes(attrs: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.EmeraldBaseEditText, 0, 0)

        required = args.getBoolean(R.styleable.EmeraldBaseEditText_required, false)
        inputType = args.getInt(R.styleable.EmeraldBaseEditText_android_inputType,
                EditorInfo.TYPE_CLASS_TEXT)
        textAttribute = args.getString(R.styleable.EmeraldBaseEditText_text) ?: ""

        args.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText?.run {
            addTextChangedListener(textWatcher)
            setOnFocusChangeListener { _, _ ->
                if (text?.isNotEmpty() == true
                        && state.state != EditTextState.InputState.DISABLED) {
                    isValid()
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        editText?.removeTextChangedListener(textWatcher)
    }

    @Deprecated("Does not handle Emerald states",
            ReplaceWith("state.setState(InputState, String)"))
    override fun setError(error: CharSequence?) {
        super.setError(error)
    }

    private fun setValidationState(isValid: Boolean, message: String) {
        if (isValid)
        // TODO esperar definição do emerald state.setState(EditTextState.InputState.SUCCESS)
        else state.setState(EditTextState.InputState.ERROR, message)
    }

    override fun isValid(): Boolean {
        var (isValid, message) = validateEditText()

        if (required && text.isEmpty()) isValid = false
        if (!required && text.isEmpty()) isValid = true

        setValidationState(isValid, message)

        return isValid
    }

    abstract fun validateEditText(): Pair<Boolean, String>
}