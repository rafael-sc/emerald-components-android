package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_emerald_masked_edit_text.*

class MaskedEditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emerald_masked_edit_text)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botao.setOnClickListener {
            emerald_masked_edit_text_phone.defineMask("[00]00")
        }

    }

}
