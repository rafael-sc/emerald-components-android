package br.com.stone.emeraldcomponents.basics.pager

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.EmeraldPagerAdapter
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 19/04/2018.
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldPagerAdapterTest {

    private lateinit var adapter: EmeraldPagerAdapter

    private lateinit var viewGroup: ConstraintLayout

    @Before
    fun setup() {
        val item = EmeraldTabPagerItem(R.layout.widget_infoblockview, { })
        adapter = EmeraldPagerAdapter(ApplicationProvider.getApplicationContext(), listOf(item))
        viewGroup = ConstraintLayout(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Should instantiate item`() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(layout, viewGroup.getChildAt(0))
    }

    @Test
    fun `Should destroy item`() {
        val layout = adapter.instantiateItem(viewGroup, 0)
        assertEquals(1, viewGroup.childCount)
        adapter.destroyItem(viewGroup, 0, layout)
        assertEquals(0, viewGroup.childCount)
    }

    @Test
    fun `Should return true when view is from object`() {
        assertTrue(adapter.isViewFromObject(viewGroup, viewGroup))
    }

    @Test
    fun `Should return false when view is not from object`() {
        assertFalse(adapter.isViewFromObject(viewGroup, View(ApplicationProvider.getApplicationContext())))
    }

    @Test
    fun `Should return 1 when adapter count is called`() {
        assertEquals(1, adapter.count)
    }

    @Test
    fun `Should return 1 when pagedWidth is set to 1 on constructor`() {
        val testWidth = 1f
        val item = EmeraldTabPagerItem(R.layout.widget_infoblockview, { })
        val adapter = EmeraldPagerAdapter(ApplicationProvider.getApplicationContext(), listOf(item), testWidth)
        assertEquals(testWidth, adapter.getPageWidth(0))
    }
}