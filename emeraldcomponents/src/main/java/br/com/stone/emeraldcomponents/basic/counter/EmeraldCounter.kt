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

    fun setup(minValue: Int, maxValue: Int, startValue: Int) {

        fun verify() {
            counterTextView.text = counter.toString()

            minusSign.isEnabled = counter > minValue
            plusSign.isEnabled = counter < maxValue
        }

        counter = startValue
        verify()

        fun click(view: View) {
            if (counter in minValue..maxValue) {
                counter += (if (view == minusSign) -1 else 1)
            }

            verify()
        }

        minusSign.setOnClickListener(::click)
        plusSign.setOnClickListener(::click)
    }
}