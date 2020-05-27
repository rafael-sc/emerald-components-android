package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_pin_view.*


class PinViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_view)


        buttonValidate.setOnClickListener {

            val pinCodeText = emeraldPinVIew.getText()
            Toast.makeText(this, "pin code text: $pinCodeText", Toast.LENGTH_SHORT).show()
        }
    }
}