package br.com.stone.emeraldcomponents.basic.pinview

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.Selection
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.dimen
import kotlin.properties.Delegates

class EmeraldPinCodeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var maxPinLengthPerView: Int = 1
    private var defaultPinCount: Int = 6
    private var editTextList = mutableListOf<EmeraldPinItemView>()
    var pinCodeCompleteListener: (code: String) -> Unit = {}
    var state: PinCodeState by Delegates.observable(PinCodeState.DEFAULT) { _, _, newValue ->
        val background = when (newValue) {
            PinCodeState.DEFAULT -> R.drawable.stroke_box_gray
            PinCodeState.ERROR -> R.drawable.stroke_box_red
        }
        editTextList.forEach {
            it.setBackgroundResource(background)
        }
    }

    init {
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmeraldPinCodeView)
            val isNumeric = attributes.getBoolean(R.styleable.EmeraldPinCodeView_isNumeric, true)
            val pinCount = attributes.getInteger(R.styleable.EmeraldPinCodeView_itemCount, defaultPinCount)
            attributes.recycle()

            init(isNumeric, pinCount)
        }
    }

    fun init(isNumeric: Boolean, itemCount: Int) {
        editTextList.clear()
        removeAllViews()
        setEditTextList(createItems(itemCount, isNumeric))
        setListener(editTextList)
    }

    private fun createItems(maxItems: Int, isNumeric: Boolean): MutableList<EmeraldPinItemView> {
        val editTextList: MutableList<EmeraldPinItemView> = mutableListOf()
        repeat((0 until maxItems).count()) {
            val editText = createPinItem(isNumeric)
            editTextList.add(editText)
            this.addView(editText)
        }
        return editTextList
    }

    private fun setListener(editTextList: MutableList<EmeraldPinItemView>) {
        val maxItems = editTextList.size
        editTextList.forEachIndexed { index, editText ->

            editText.onDelPressed = {
                if (index > 0)
                    if (editText.text?.length == 0) {
                        editTextList[index - 1].apply {
                            this.text?.clear()
                            requestFocus()
                        }
                    }
            }

            editText.onTextPasted = {
                setCode(it)
            }

            editText.requestFocusOnNext = {
                if (index < maxItems - 1)
                    editTextList[index + 1].apply {
                        Selection.setSelection(text, text!!.length)
                        requestFocus()
                    }
            }

            editText.handleFocus(
                    editTextList.getOrNull(index + 1),
                    editTextList.getOrNull(index - 1)
            )
        }
    }

    private fun createPinItem(isNumeric: Boolean): EmeraldPinItemView {
        @SuppressLint("InflateParams")
        val editText: EmeraldPinItemView = LayoutInflater.from(context)
                .inflate(R.layout.emerald_pin_item, null) as EmeraldPinItemView

        val layoutParams = LayoutParams(
                context.dimen(R.dimen.pin_item_height).toInt(),
                context.dimen(R.dimen.pin_item_width).toInt())

        layoutParams.setMargins(context.dimen(R.dimen.pin_item_margin).toInt(),
                0,
                context.dimen(R.dimen.pin_item_margin).toInt(),
                0)

        editText.layoutParams = layoutParams

        if (isNumeric)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        else
            editText.inputType = InputType.TYPE_CLASS_TEXT

        editText.id = ViewCompat.generateViewId()

        return editText
    }

    private fun handlePasteText(text: String, editTextList: MutableList<EmeraldPinItemView>) {
        editTextList.forEachIndexed { index, editText ->
            if (text.length > index) {
                editText.setText(text[index].toString())
            }
        }
    }

    private fun AppCompatEditText.handleFocus(
            nextEditText: AppCompatEditText?,
            previousEditText: AppCompatEditText?
    ) {
        this.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->

            if (hasFocus && this.text.toString().isNotEmpty() && nextEditText != null)
                nextEditText.requestFocus()

            if (hasFocus && this.text.toString().isEmpty() && previousEditText != null
                    && previousEditText.text.toString().isEmpty())
                previousEditText.requestFocus()
        }

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (text?.length == maxPinLengthPerView && allItemsFilled()) {
                    pinCodeCompleteListener(getCode())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (text?.length == maxPinLengthPerView)
                    nextEditText?.requestFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                state = PinCodeState.DEFAULT
                when {
                    text.toString().length == maxPinLengthPerView -> {
                        nextEditText?.requestFocus()
                    }
                    text.toString().isEmpty() ->
                        previousEditText?.requestFocus()
                }
            }
        })
    }

    private fun allItemsFilled(): Boolean {
        editTextList.forEach {
            if (it.text?.length == 0)
                return false
        }
        return true
    }

    fun setCode(code: String) {
        handlePasteText(code, editTextList)
    }

    fun getCode(): String {
        var code = ""
        editTextList.forEach {
            code = code.plus(it.text)
        }
        return code
    }

    private fun setEditTextList(itemViewList: MutableList<EmeraldPinItemView>) {
        editTextList = itemViewList
    }
}
