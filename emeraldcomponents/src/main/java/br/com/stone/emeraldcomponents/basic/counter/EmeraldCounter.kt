package br.com.stone.emeraldcomponents.basic.counter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_counter.view.*

class EmeraldCounter : ConstraintLayout {

    var counter: Int = 0
        private set

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_counter, this)
    }

    fun setup(leftLimiter: Int, rightLimiter: Int, startValue: Int) {

        counterTextView.text = startValue.toString()
        counter = startValue

        minusSign.setOnClickListener {
            if (counter > leftLimiter) {
                counter--
                if (counter == leftLimiter) {
                    minusSign.isClickable = false
                    minusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_gray))

                }
                if (counter < rightLimiter) {
                    plusSign.isClickable = true
                    plusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_button_primary))
                }
                counterTextView.text = counter.toString()
            }
        }

        plusSign.setOnClickListener {
            counter++
            if (counter > leftLimiter && !minusSign.isClickable) {
                minusSign.isClickable = true
                minusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_button_primary))
            }
            if (counter == rightLimiter) {
                plusSign.isClickable = false
                plusSign.setTextColor(ContextCompat.getColor(context, R.color.emerald_gray))
            }
            counterTextView.text = counter.toString()
        }
    }
}