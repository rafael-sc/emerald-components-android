package br.com.stone.emeraldcomponents.basic.input

import android.content.Context
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import androidx.appcompat.widget.AppCompatEditText
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.common.UtilValidator
import com.redmadrobot.inputmask.MaskedTextChangedListener

/**
 * Created by renan.silva on 21/02/2018.
 */
class EmeraldMaskedEditText : AppCompatEditText {

    private var mask: String? = null
    private var acceptableTextLength = 0
    private var showHint: Boolean = true
    private var showError: Boolean = true

    var unmaskedText = ""
        private set

    var type: MaskTypes = MaskTypes.NONE
        set(value) {
            field = value
            defineMask(field.mask)
        }

    var errorMessage: String = ""
        private set

    private var textListener: TextWatcher? = null

    var fillSequence = DEFAULT_FILL_SEQUENCE
    var fillLength = DEFAULT_FILL_LENGTH

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    init {
        if (showError) {
            onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) isValid()
            }
        }
    }

    private fun init(attrs: AttributeSet) {
        setAttributes(attrs)
        defineMask(mask)
    }

    private fun setAttributes(attributeSet: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attributeSet, R.styleable.EmeraldMaskedEditText, 0, 0)

        type = MaskTypes.getById(args.getInt(R.styleable.EmeraldMaskedEditText_maskType, MaskTypes.NONE.id))
        mask = args.getString(R.styleable.EmeraldMaskedEditText_mask) ?: type.mask
        showHint = args.getBoolean(R.styleable.EmeraldMaskedEditText_showHint, true)
        showError = args.getBoolean(R.styleable.EmeraldMaskedEditText_showError, true)
        fillLength = args.getInt(R.styleable.EmeraldMaskedEditText_fillLength, 1)

        args.recycle()
    }

    fun defineMask(mask: String?) {
        removeTextChangedListener(textListener)
        val valueListener: (String) -> Unit = { unmaskedText = it }
        textListener = when (type) {
            MaskTypes.CURRENCY -> {
                CurrencyTextWatcher(this, valueListener = valueListener).apply {
                    addTextChangedListener(this)
                    setText("0")
                    inputType = InputType.TYPE_CLASS_NUMBER
                }
            }
            MaskTypes.PRE_FILL -> {
                PreFillTextWatcher(this, fillSequence, fillLength, valueListener).apply {
                    addTextChangedListener(this)
                    text = text
                }
            }
            else -> {
                mask?.let { addMask(it) }.apply {
                    acceptableTextLength = this?.acceptableTextLength() ?: 0
                }
            }
        }
    }

    private fun addMask(mask: String): MaskedTextChangedListener {
        val listener = MaskedTextChangedListener(
                mask,
                true,
                this,
                null,
                object : MaskedTextChangedListener.ValueListener {

                    override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                        unmaskedText = extractedValue
                    }
                }
        )
        addTextChangedListener(listener)
        if (hint == null && showHint) hint = listener.placeholder()
        return listener
    }

    fun isValid(): Boolean {
        val isValid = if (text?.length ?: 0 < acceptableTextLength) {
            errorMessage = context.getString(R.string.emerald_mask_error)
            false
        } else if (type == MaskTypes.EMAIL && !UtilValidator.isEmailValid(text.toString())) {
                errorMessage = context.getString(R.string.emerald_invalid_email)
                false
            } else {
                errorMessage = ""
                true
            }

        if (showError) {
            error = if (isValid) null else errorMessage
        }
        return isValid
    }

    companion object {
        const val MAX_CURRENCY_LENGTH = 14
        private const val DEFAULT_FILL_SEQUENCE = '0'
        private const val DEFAULT_FILL_LENGTH = 1

        private const val NONE_ID = 0
        private const val PHONENUMBER_ID = 1
        private const val CELLPHONENUMBER_ID = 2
        private const val EMAIL_ID = 3
        private const val CPF_ID = 4
        private const val CNPJ_ID = 5
        private const val CEP_ID = 6
        private const val TEXT_ID = 7
        private const val CURRENCY_ID = 8
        private const val PRE_FILL_ID = 9
        private const val CREDIT_CARD_ID = 10
        private const val CREDIT_CARD_EXP_DATE_ID = 11
        private const val CREDIT_CARD_CVV_ID = 12
    }

    enum class MaskTypes(val id: Int, val mask: String?) {
        NONE(NONE_ID, null),
        PHONENUMBER(PHONENUMBER_ID, "([00]) [0000]-[0000]"),
        CELLPHONENUMBER(CELLPHONENUMBER_ID, "([00]) [00000]-[0000]"),
        EMAIL(EMAIL_ID, null),
        CPF(CPF_ID, "[000].[000].[000]-[00]"),
        CNPJ(CNPJ_ID, "[00].[000].[000]/[0000]-[00]"),
        CEP(CEP_ID, "[00000]-[000]"),
        TEXT(TEXT_ID, "[…]"),
        CURRENCY(CURRENCY_ID, null),
        PRE_FILL(PRE_FILL_ID, null),
        CREDIT_CARD(CREDIT_CARD_ID, "[0000] [0000] [0000] [0000]"),
        CREDIT_CARD_EXP_DATE(CREDIT_CARD_EXP_DATE_ID, "[00]/[00]"),
        CREDIT_CARD_CVV(CREDIT_CARD_CVV_ID, "[000]");

        companion object {
            fun getById(id: Int?) = values().firstOrNull { it.id == id } ?: NONE
        }
    }
}