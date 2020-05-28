package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.pinview.PinCodeCompleteListener
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_pin_view.*


class PinViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_view)

        emeraldPinCode.setListener(object : PinCodeCompleteListener {
            override fun onCodeComplete(code: String) = validate(code)
        })


        buttonValidate.setOnClickListener {
            validate(emeraldPinCode.getCode())
        }
    }

    fun validate(code: String) {
        if (code != "1234") {
            emeraldPinCode.setErrorState()
            Toast.makeText(this, "this code is invalid, try 1234. typed code:$code", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "this code is valid. typed code:$code", Toast.LENGTH_SHORT).show()
            emeraldPinCode.setDefaultState()
        }
    }
}