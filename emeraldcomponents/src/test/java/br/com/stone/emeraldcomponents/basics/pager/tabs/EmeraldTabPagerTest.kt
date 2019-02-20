package br.com.stone.emeraldcomponents.basics.pager.tabs

import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.bullet.EmeraldBulletPager
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPager
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import kotlinx.android.synthetic.main.widget_tab_pager.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 19/04/2018.
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldTabPagerTest {

    private lateinit var pager: EmeraldTabPager

    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        activity.setTheme(R.style.Base_Theme_AppCompat)
        pager = EmeraldTabPager(activity)
    }

    @Test
    fun `Should not be null when instanced with context`() {
        Assert.assertNotNull(pager)
    }

    @Test
    fun `Should not be null when instanced with context and attribute set`() {
        val pager = EmeraldBulletPager(activity, Mockito.mock(AttributeSet::class.java))
        Assert.assertNotNull(pager)
    }

    @Test
    fun `Should set tabs when set adapter is called`() {
        val testTitle = "test"
        val item = EmeraldTabPagerItem(R.layout.widget_autocomplete,
                { }, testTitle, R.drawable.abc_ic_ab_back_material)
        pager.setAdapter(listOf(item))
        assertEquals(testTitle, pager.emeraldTabLayout.getTabAt(0)?.text)
    }
}