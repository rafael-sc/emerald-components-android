package br.com.stone.emeraldcomponents.basic.counter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_counter.view.*

class EmeraldCounter : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_counter, this)

        counterTextView.text = "1"

        minusSign.setOnClickListener {
            var decrement: Int = counterTextView.text.toString().toInt()
            if (decrement > 0) {
                decrement--
                if (decrement == 0) {
                    minusSign.isClickable = false
                    minusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_gray))

                }
                counterTextView.text = decrement.toString()
            }
        }

        plusSign.setOnClickListener {
            var increment: Int = counterTextView.text.toString().toInt()
            increment++
            if (increment > 0 && !minusSign.isClickable) {
                minusSign.isClickable = true
                minusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_button_primary))
            }
            counterTextView.text = increment.toString()
        }
    }

    fun getCounterValue(): Int {
        return counterTextView.text.toString().toInt()
    }
}