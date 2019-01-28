package br.com.stone.emeraldcomponents.basics

import android.support.constraint.ConstraintLayout
import android.view.View
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerAdapter
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 19/04/2018.
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldPagerAdapterTest {

    private lateinit var adapter: EmeraldTabPagerAdapter

    private lateinit var viewGroup: ConstraintLayout

    @Before
    fun setup() {
        val item = EmeraldTabPagerItem(R.layout.widget_infoblockview, { })
        adapter = EmeraldTabPagerAdapter(RuntimeEnvironment.application, listOf(item))
        viewGroup = ConstraintLayout(RuntimeEnvironment.application)
    }

    @Test
    fun testInstantiateItem() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(layout, viewGroup.getChildAt(0))
    }

    @Test
    fun testDestroyItem() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(1, viewGroup.childCount)
        adapter.destroyItem(viewGroup, 0, layout)
        assertEquals(0, viewGroup.childCount)
    }

    @Test
    fun testIsViewFromObject() {
        assertTrue(adapter.isViewFromObject(viewGroup, viewGroup))
        assertFalse(adapter.isViewFromObject(viewGroup, View(RuntimeEnvironment.application)))
    }

    @Test
    fun testGetCount() {
        assertEquals(1, adapter.count)
    }
}