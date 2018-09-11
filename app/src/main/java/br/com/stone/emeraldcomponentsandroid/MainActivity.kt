package br.com.stone.emeraldcomponentsandroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponents.extension.setUp
import br.com.stone.emeraldcomponentsandroid.activities.InputActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_activity_list.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activities = listOf(
                InputActivity::class.java to "Inputs"
        )

        recyclerMain.setUp(activities,
                { R.layout.item_activity_list },
                {
                    buttonItem.text = it.second
                    buttonItem.setOnClickListener { _ ->
                        startActivity(Intent(this@MainActivity, it.first))
                    }
                }, {})
    }
}
