package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_auto_complete.*


class AutoCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)
        autoCompleteView.setItems(setOf(
                Bank("101", "Banco do Brasil"),
                Bank("104", "Senta André"),
                Bank("330", "Caixa Economica Federal"),
                Bank("251", "Bradesco"),
                Bank("165", "Itaú")),
                callback = {
                    Log.i("AUTOCOMPLETE", it.desc)
                })

        AutoCompleteViewButton.setOnClickListener {
            Log.i("Valid", autoCompleteView.isValid().toString())
        }
    }

    data class Bank(val id: String, val desc: String) {
        override fun toString(): String {
            return "$id - $desc"
        }
    }

}

