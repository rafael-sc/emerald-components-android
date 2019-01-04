package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_spinner.*

class SpinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        emeraldSpinner.setItems(listOf("Option 1","Option 2"))
        emeraldSpinnerWithPlaceholder.setItems(listOf("Option 1","Option 2"))

        emeraldAutoComplete.setItems(setOf(
                Value("100", "Aaaaaaaaa"),
                Value("200", "Bbbbbbbbb"),
                Value("300", "Ccccccccc"),
                Value("400", "Ddddddddd"),
                Value("500", "Eeeeeeeee"),
                Value("600", "Fffffffff"),
                Value("700", "Ggggggggg"),
                Value("800", "Hhhhhhhhh"),
                Value("900", "Ijklmnopq")),
                callback = {
                    Log.i("AUTOCOMPLETE", it.desc)
                })
    }


    data class Value(val id: String, val desc: String) {
        override fun toString(): String {
            return "$id - $desc"
        }
    }
}
