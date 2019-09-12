package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_hidealble_label.*

class HideableLabelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hidealble_label)

        emeraldHideableLabel.text = "100.111.221,99"
        button3.setOnClickListener {
            emeraldHideableLabel.hidden = !emeraldHideableLabel.hidden
            val visible = emeraldHideableLabel.emeraldHideableLabel.visibility == View.VISIBLE
            Toast.makeText(
                    this@HideableLabelActivity,
                    "Text is $visible",
                    Toast.LENGTH_SHORT).show()
        }
    }
}
