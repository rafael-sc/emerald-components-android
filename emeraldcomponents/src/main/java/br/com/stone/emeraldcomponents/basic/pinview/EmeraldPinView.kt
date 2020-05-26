package br.com.stone.emeraldcomponents.basic.pinview


import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.Selection
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.dimen

class EmeraldPinView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val TAG = "EmeraldPinView"
    val view: View? = LayoutInflater.from(context).inflate(R.layout.emerald_pin_view, this, true)
    private var maxPinLengthPerView: Int = 1


    init {
        if (attrs != null) {
            var isNumeric = false
            var pinCount = 6

            val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmeraldPinView)

            if (attributes.hasValue(R.styleable.EmeraldPinView_isNumeric)) {
                isNumeric = attributes.getBoolean(R.styleable.EmeraldPinView_isNumeric, true)
            }

            if (attributes.hasValue(R.styleable.EmeraldPinView_itemCount)) {
                pinCount = attributes.getInteger(R.styleable.EmeraldPinView_itemCount, 6)
            }
            attributes.recycle()
            val editTextList = createItems(pinCount, isNumeric)
            setListener(editTextList)
        }
    }

    private fun createItems(maxItems: Int, isNumeric: Boolean): MutableList<EmeraldPinItem> {
        val editTextList: MutableList<EmeraldPinItem> = mutableListOf()
        for (index in 0 until maxItems) {
            val editText = createPinItem(index, isNumeric)
            editTextList.add(editText)
            this.addView(editText)
        }

        return editTextList
    }

    private fun setListener(editTextList: MutableList<EmeraldPinItem>) {

        val maxItems = editTextList.size
        editTextList.forEachIndexed { index, editText ->
            editText.setListener(object : PinItemEventListener {
                override fun onTextPasted(text: String) {
                    handlePasteText(text, editTextList)
                }

                override fun onDelPressed() {
                    if (index > 0)
                        if (editText.text?.length == 0) {
                            editTextList[index - 1].apply {
                                Selection.setSelection(text, text!!.length)
                                requestFocus()
                            }
                        }
                }

                override fun requestFocusOnNext() {
                    if (index < maxItems - 1)
                        editTextList[index + 1].apply {
                            Selection.setSelection(text, text!!.length)
                            requestFocus()
                        }
                }
            })

            editText.handleFocus(
                    editTextList.getOrNull(index + 1),
                    editTextList.getOrNull(index - 1)
            )

        }
    }


    fun createPinItem(index: Int, isNumeric: Boolean): EmeraldPinItem {
        val editText: EmeraldPinItem = LayoutInflater.from(context).inflate(R.layout.emerald_pin_item, null) as EmeraldPinItem

        val layoutParams = LayoutParams(
                context.dimen(R.dimen.pin_item_height).toInt(),
                context.dimen(R.dimen.pin_item_width).toInt())
        if (index != 0) { //prevent margin on first item
            layoutParams.setMargins(context.dimen(R.dimen.pin_item_margin_start).toInt(),
                    0,
                    0,
                    0)
        }
        editText.layoutParams = layoutParams

        if (isNumeric)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        else
            editText.inputType = InputType.TYPE_CLASS_TEXT

        editText.id = ViewCompat.generateViewId()

        return editText
    }

    fun handlePasteText(text: String, editTextList: MutableList<EmeraldPinItem>) {
        editTextList.forEachIndexed { index, editText ->
            if (text.length > index)
                editText.setText(text[index].toString())
        }
    }


    private fun AppCompatEditText.handleFocus(
            nextEditText: AppCompatEditText?,
            previousEditText: AppCompatEditText?
    ) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (nextEditText == null)
                    if (text?.length == maxPinLengthPerView) {
                        Log.d(TAG, "validate")
                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when {
                    text.toString().length == maxPinLengthPerView -> {
                        nextEditText?.let {
                            Selection.setSelection(it.text, it.text!!.length)
                            it.requestFocus()
                        }
                    }
                    text.toString().isEmpty() ->
                        previousEditText?.let {
                            Selection.setSelection(it.text, it.text!!.length)
                            it.requestFocus()
                        }
                }
            }
        })
    }


    private fun allItemsFilled(editTextList: MutableList<EmeraldPinItem>): Boolean {
        editTextList.forEach {
            if (it.text?.length == 0)
                return false
        }
        return true
    }

}
