package br.com.stone.emeraldcomponentsandroid.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponents.basic.pager.bullet.EmeraldBulletPagerItem
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_pager.*
import kotlinx.android.synthetic.main.pager_test_layout.view.*

class PagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        setBulletPager()

        setTabPager()
    }

    private fun setBulletPager() {
        val item = EmeraldBulletPagerItem(R.layout.pager_test_layout) {
            testText.text = "left text"
            testText2.text = "right text"
        }

        val item2 = EmeraldBulletPagerItem(R.layout.pager_test_layout_image) {
        }

        val item3 = EmeraldBulletPagerItem(R.layout.pager_test_layout) {
            testText.text = "I'm the same layout on the first tab"
            testText2.text = "But cooler"
        }

        emeraldBulletPager.setAdapter(listOf(item, item2, item3))
    }


    private fun setTabPager() {
        val item = EmeraldTabPagerItem(R.layout.pager_test_layout, {
            testText.text = "left text"
            testText2.text = "right text"
        }, "tab with only text")

        val item2 = EmeraldTabPagerItem(R.layout.pager_test_layout_image, {
        }, iconId = R.mipmap.ic_launcher)

        val item3 = EmeraldTabPagerItem(R.layout.pager_test_layout, {
            testText.text = "I'm the same layout on the first tab"
            testText2.text = "But cooler"
        }, "tab with icon", R.drawable.ic_launcher_background)

        emeraldTabPager.setAdapter(listOf(item, item2, item3))
    }
}
