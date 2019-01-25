package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerItem
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_pager_tabs.*
import kotlinx.android.synthetic.main.pager_test_layout.view.*

class PagerTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager_tabs)

        val item = EmeraldPagerItem(R.layout.pager_test_layout) {
            it.testText.text = "left text"
            it.testText2.text = "right text"
        }

        val item2 = EmeraldPagerItem(R.layout.pager_test_layout_image) {
        }

        val item3 = EmeraldPagerItem(R.layout.pager_test_layout, bindValues = {
            it.testText.text = "I'm the same layout on the first tab"
            it.testText2.text = "But cooler"
        })

        emeraldTabPager.setAdapter(listOf(item, item2, item3))
    }
}
