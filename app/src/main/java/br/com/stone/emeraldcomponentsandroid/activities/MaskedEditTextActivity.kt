package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_emerald_masked_edit_text.*

class MaskedEditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emerald_masked_edit_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        emerald_masked_edit_text_autofill.autofillLength = 4
        emerald_masked_edit_text_autofill.autofillSequence = "0"
        emerald_masked_edit_text_autofill.type = EmeraldMaskedEditText.MaskTypes.AUTO_FILL

        botao.setOnClickListener {
            emerald_masked_edit_text_phone.defineMask("[00]")
        }
    }

}
