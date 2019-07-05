package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_emerald_edittext.*

/**
 * Created by lucas.amaral on 13/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class EditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emerald_edittext)

        emeraldEditText.text = "21999999999"

        checkBox.isChecked = emeraldEditText.required
        checkBox.setOnCheckedChangeListener{ _, checked ->
            emeraldEditText.required = checked
        }

        editText.setOnEditorActionDone {
            Toast.makeText(this,"keyboard done clicked",Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            Log.i("Valid", emeraldEditText.isValid().toString())
        }
    }
}