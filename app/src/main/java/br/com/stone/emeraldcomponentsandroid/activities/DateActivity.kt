package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_date.*

class DateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        emeraldDateSelector.navigateUsingMonths = false

        emeraldDateSelector.setDateChangedListener {
            Toast.makeText(this, it.format("dd/MM/yy")
                    , Toast.LENGTH_SHORT).show()
        }

        emeraldDateFilter.setOnFilterChangedListener { startDate, endDate ->
            if (startDate == endDate) Toast.makeText(this, startDate.format("dd/MM/yy")
                        , Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,
                    startDate.format("dd/MM/yy") + " até " + endDate.format("dd/MM/yy")
                    , Toast.LENGTH_SHORT).show()
        }
    }
}
