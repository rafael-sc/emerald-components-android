package br.com.stone.emeraldcomponents.basic.counter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
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

        fun click(view: View) {
            if(counter in leftLimiter..rightLimiter) {
                counter += (if (view == minusSign) -1 else 1)
            }

            counterTextView.text = counter.toString()

            minusSign.isEnabled = counter > leftLimiter
            plusSign.isEnabled = counter < rightLimiter
        }

        minusSign.setOnClickListener(::click)
        plusSign.setOnClickListener(::click)
    }
}