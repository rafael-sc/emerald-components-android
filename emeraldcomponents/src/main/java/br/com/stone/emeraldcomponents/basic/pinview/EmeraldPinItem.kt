package br.com.stone.emeraldcomponents.basic.pinview


import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText

class EmeraldPinItem : AppCompatEditText {
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
                val text = primaryClip!!.getItemAt(0).text.toString()
                pinItemEventListener.onTextPasted(text)
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_DEL) {
            pinItemEventListener.onDelPressed()
            return false
        }
        return super.dispatchKeyEventPreIme(event)
    }


}