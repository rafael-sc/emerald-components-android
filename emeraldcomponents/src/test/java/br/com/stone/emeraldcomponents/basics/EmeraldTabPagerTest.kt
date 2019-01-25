package br.com.stone.emeraldcomponents.basics

import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.EmeraldBulletPager
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerItem
import org.junit.Assert
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

    private lateinit var pager: EmeraldBulletPager

    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        activity.setTheme(R.style.Base_Theme_AppCompat)
        pager = EmeraldBulletPager(activity)
    }

    @Test
    fun testNotNull() {
        Assert.assertNotNull(pager)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val pager = EmeraldBulletPager(activity, Mockito.mock(AttributeSet::class.java))
        Assert.assertNotNull(pager)
    }

    @Test
    fun testSetAdapter() {
        val item = EmeraldPagerItem(R.layout.widget_autocomplete) { }
        pager.setAdapter(listOf(item))
    }
}