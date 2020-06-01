package br.com.stone.emeraldcomponents.basic.pinview

import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText

class EmeraldPinItemView : AppCompatEditText {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
            context,
            attrs
    )

    constructor(
            context: Context?,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    private lateinit var pinItemEventListener: PinItemEventListener

    fun setListener(listener: PinItemEventListener) {
        pinItemEventListener = listener
    }

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
                val text = primaryClip!!.getItemAt(0).text.toString().trim()
                if (text.isNotBlank())
                    pinItemEventListener.onTextPasted(text)
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.action != KeyEvent.ACTION_DOWN)
            return true
        return if (event.keyCode != KeyEvent.KEYCODE_DEL && text?.length == 1) {
            pinItemEventListener.requestFocusOnNext()
            false
        } else if (event.keyCode == KeyEvent.KEYCODE_DEL && text?.length == 0) {
            pinItemEventListener.onDelPressed()
            false
        } else
            super.dispatchKeyEvent(event)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        this.setSelection(this.text.toString().length)
        super.onSelectionChanged(selStart, selEnd)
    }
}