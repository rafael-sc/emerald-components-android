package br.com.stone.emeraldcomponents.basics.pager.tabs

import android.view.View
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by renan.silva on 19/04/2018.
 */
class EmeraldTabPagerItemTest {

    @Test
    fun `Should instantiate correctly`() {
        val layoutId = 0
        val bindValues = { _: View -> }
        val title = "title"
        val iconId = 0
        val item = EmeraldTabPagerItem(layoutId, bindValues, title, iconId)

        assertEquals(layoutId, item.layoutId)
        assertEquals(bindValues, item.bindValues)
        assertEquals(title, item.title)
        assertEquals(iconId, item.iconId)
    }
}