package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
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
            Toast.makeText(
                    this@HideableLabelActivity,
                    "${emeraldHideableLabel.emeraldHideableLabel.visibility}",
                    Toast.LENGTH_SHORT).show()
        }
    }
}
