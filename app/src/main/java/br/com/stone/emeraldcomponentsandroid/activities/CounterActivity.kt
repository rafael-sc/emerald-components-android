package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}