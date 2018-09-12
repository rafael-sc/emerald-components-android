package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Input"
    }
}
