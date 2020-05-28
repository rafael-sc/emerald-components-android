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

        emeraldPinCodeView.pinCodeCompleteListener = {
            validate(it)
        }

        buttonValidate.setOnClickListener {
            validate(emeraldPinCodeView.getCode())
        }
    }

    private fun validate(code: String) {
        if (code != "1234") {
            emeraldPinCodeView.setErrorState()
            Toast.makeText(this, "this code is invalid, try 1234. typed code:$code", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "this code is valid. typed code:$code", Toast.LENGTH_SHORT).show()
            emeraldPinCodeView.setDefaultState()
        }
    }
}