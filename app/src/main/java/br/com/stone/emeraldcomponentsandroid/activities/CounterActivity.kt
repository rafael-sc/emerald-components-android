package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        terminalCounter.text = "1"

        minusSign.setOnClickListener {
            var decrement: Int = terminalCounter.text.toString().toInt()
            if (decrement > 0) {
                decrement--
                if (decrement == 0) {
                    minusSign.isClickable = false
                    minusSign.setTextColor(ContextCompat.getColor(this, R.color.emerald_gray))

                }
                terminalCounter.text = decrement.toString()
            }
        }

        plusSign.setOnClickListener {
            var increment: Int = terminalCounter.text.toString().toInt()
            increment++
            if (increment > 0 && !minusSign.isClickable) {
                minusSign.isClickable = true
                minusSign.setTextColor(ContextCompat.getColor(this, R.color.emerald_button_primary))
            }
            terminalCounter.text = increment.toString()
        }
    }
}
