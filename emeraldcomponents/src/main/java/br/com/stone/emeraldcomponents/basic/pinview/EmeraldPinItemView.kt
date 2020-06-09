package br.com.stone.emeraldcomponents.basic.pinview

import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.numberfy

class EmeraldPinItemView : AppCompatEditText {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
            context,
            attrs
    )

    constructor(
            context: Context?,
            attrs: AttributeSet?,
            defStyleAttr: Int = R.style.PinItem
    ) : super(context, attrs, defStyleAttr)

    var onTextPasted: (code: String) -> Unit = {}
    var requestFocusOnNext: () -> Unit = {}
    var onDelPressed: () -> Unit = {}

    override fun onTextContextMenuItem(id: Int): Boolean {
        val consumed = super.onTextContextMenuItem(id)
        when (id) {
            android.R.id.paste -> onTextPaste()
        }
        return consumed
    }

    private fun onTextPaste() {
        val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.apply {
            if (hasPrimaryClip() && primaryClipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)!!) {
                var text = primaryClip!!.getItemAt(0).text.toString().trim()
                text = validatePastedText(text)
                if (text.isNotEmpty())
                    onTextPasted(text)
            }
        }
    }

    private fun validatePastedText(text: String): String {
        return if (inputType == InputType.TYPE_CLASS_NUMBER)
            text.numberfy()
        else
            text
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.action != KeyEvent.ACTION_DOWN)
            return true
        return if (event.keyCode != KeyEvent.KEYCODE_DEL && text?.length == 1) {
            requestFocusOnNext()
            false
        } else if (event.keyCode == KeyEvent.KEYCODE_DEL && text?.length == 0) {
            onDelPressed()
            false
        } else
            super.dispatchKeyEvent(event)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        this.setSelection(this.text.toString().length)
        super.onSelectionChanged(selStart, selEnd)
    }
}