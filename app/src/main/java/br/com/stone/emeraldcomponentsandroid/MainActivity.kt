package br.com.stone.emeraldcomponentsandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.extension.setUp
import br.com.stone.emeraldcomponentsandroid.activities.ButtonActivity
import br.com.stone.emeraldcomponentsandroid.activities.CalendarActivity
import br.com.stone.emeraldcomponentsandroid.activities.DateActivity
import br.com.stone.emeraldcomponentsandroid.activities.EndlessRecyclerActivity
import br.com.stone.emeraldcomponentsandroid.activities.HideableLabelActivity
import br.com.stone.emeraldcomponentsandroid.activities.InputActivity
import br.com.stone.emeraldcomponentsandroid.activities.LabelActivity
import br.com.stone.emeraldcomponentsandroid.activities.MaskedEditTextActivity
import br.com.stone.emeraldcomponentsandroid.activities.PagerActivity
import br.com.stone.emeraldcomponentsandroid.activities.SpinnerActivity
import br.com.stone.emeraldcomponentsandroid.activities.CounterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_activity_list.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activities = listOf(
                CounterActivity::class.java to "Counter",
                EndlessRecyclerActivity::class.java to "Endless Recycler",
                MaskedEditTextActivity::class.java to "Masked",
                ButtonActivity::class.java to "Button",
                CalendarActivity::class.java to "Calendar + Date Event List",
                InputActivity::class.java to "Inputs",
                SpinnerActivity::class.java to "Spinner",
                DateActivity::class.java to "Date Selector and Filter",
                PagerActivity::class.java to "Pager",
                LabelActivity::class.java to "Label",
                HideableLabelActivity::class.java to "Hideable Label"
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
