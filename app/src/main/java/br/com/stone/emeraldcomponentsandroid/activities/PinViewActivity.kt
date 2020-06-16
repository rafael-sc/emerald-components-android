package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.pinview.EmeraldPinCodeView
import br.com.stone.emeraldcomponents.basic.pinview.PinCodeState
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_pin_view.*


class PinViewActivity : AppCompatActivity() {

    private lateinit var emeraldPinCodeView: EmeraldPinCodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_view)

        emeraldPinCodeView = EmeraldPinCodeView(this)
        emeraldPinCodeView.apply {
            init(true, 4)
            pinCodeCompleteListener = {
                validate(it)
            }
        }
        emeraldPinCodeContainer.addView(emeraldPinCodeView)

        buttonValidate.setOnClickListener {
            validate(emeraldPinCodeView.getCode())
        }
    }

    private fun validate(code: String) {
        if (code != "1234") {
            emeraldPinCodeView.state = PinCodeState.ERROR
            Toast.makeText(this, "this code is invalid, try 1234. typed code:$code", Toast.LENGTH_SHORT).show()
        } else {
            emeraldPinCodeView.state = PinCodeState.DEFAULT
            Toast.makeText(this, "this code is valid. typed code:$code", Toast.LENGTH_SHORT).show()
        }
    }
}