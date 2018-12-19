package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.EmeraldButton
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_emerald_button.*

class ButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emerald_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        emeraldButton6.setStyleProperties(R.color.emerald_primary,R.color.emerald_white_1,12f)

        emeraldButtonOutline4.style =EmeraldButton.ButtonStyle.OUTLINE
        emeraldButtonOutline4.setStyleProperties(R.color.emerald_attention,R.color.emerald_attention,30f)
    }

}
