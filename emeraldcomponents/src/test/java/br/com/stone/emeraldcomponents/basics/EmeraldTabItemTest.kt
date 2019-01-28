package br.com.stone.emeraldcomponents.basics

import android.view.View
import br.com.stone.emeraldcomponents.basic.pager.tabs.EmeraldTabPagerItem
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by renan.silva on 19/04/2018.
 */
class EmeraldTabItemTest {

    @Test
    fun testInstance() {
        val layoutId = 0
        val bindValues = { _: View -> }
        val title = "title"
        val iconId = 0
        val item = EmeraldTabPagerItem(layoutId, bindValues)

        assertEquals(layoutId, item.layoutId)
        assertEquals(bindValues, item.bindValues)
    }
}