package br.com.stone.emeraldcomponents.basic.pinview


import android.content.Context
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import br.com.stone.emeraldcomponents.R

class EmeraldPinView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val TAG = "EmeraldPinView"
    val view: View? = LayoutInflater.from(context).inflate(R.layout.emerald_pin_view, this, true)
    private var editTextList: MutableList<EmeraldPinItem> = mutableListOf()
    private var maxPinLengthPerView: Int = 1
    private var isNumeric = false

    init {

        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmeraldPinView)

            if (attributes.hasValue(R.styleable.EmeraldPinView_isNumeric)) {
                isNumeric = attributes.getBoolean(R.styleable.EmeraldPinView_isNumeric, true)
            }

            if (attributes.hasValue(R.styleable.EmeraldPinView_maxLengthPerItem)) {
                maxPinLengthPerView = attributes.getInteger(R.styleable.EmeraldPinView_maxLengthPerItem, 1)
            }

            if (attributes.hasValue(R.styleable.EmeraldPinView_itemCount)) {
                val maxItems = attributes.getInteger(R.styleable.EmeraldPinView_itemCount, 6)


                for (index in 0 until maxItems) {
                    val editText = createPinItem()
                    editTextList.add(editText)
                    this.addView(editText, index)
                }
            }
            attributes.recycle()
        }
        editTextList.forEachIndexed { index, editText ->
            editText.setListener(object : PinItemEventListener {
                override fun onTextPasted(text: String) {
                    handlePasteText(text)
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
            })

            editText.handleFocus(
                    editTextList.getOrNull(index + 1),
                    editTextList.getOrNull(index - 1)
            )

        }
    }

    fun createPinItem(): EmeraldPinItem {
        val editText = EmeraldPinItem(ContextThemeWrapper(context, R.style.PinItem))
        editText.id = ViewCompat.generateViewId()
        return editText
    }

    fun handlePasteText(text: String) {
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
                if (nextEditText == null && text?.length == maxPinLengthPerView) {
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
}
