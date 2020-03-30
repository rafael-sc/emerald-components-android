package br.com.stone.emeraldcomponentsandroid.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_recycler_pager.*
import kotlinx.android.synthetic.main.pager_test_layout.view.*

class RecyclerPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_pager)
        setTabPager()
    }

    private fun setTabPager() {
        val item = EmeraldTabPagerItem(R.layout.recycler_pager_test_layout, {
            testText.text = "left text"
            testText2.text = "right text"
        }, "tab with only text")

        val item2 = EmeraldTabPagerItem(R.layout.recycler_pager_test_layout_image, {
        }, iconId = R.mipmap.ic_launcher)

        val item3 = EmeraldTabPagerItem(R.layout.recycler_pager_test_layout, {
            testText.text = "I'm the same layout on the first tab"
            testText2.text = "But cooler"
        }, "tab with icon", R.drawable.ic_launcher_background)

        emeraldTabPager.setAdapter(listOf(item, item2, item3))

        emeraldTabPager.tabLayout.run {
            setBackgroundColor(colorRes(R.color.emerald_primary))
            setSelectedTabIndicatorColor(colorRes(R.color.emerald_attention))
            setTabTextColors(Color.BLACK, Color.WHITE)
        }
        emeraldTabPager.selectTab(1)
    }
}
