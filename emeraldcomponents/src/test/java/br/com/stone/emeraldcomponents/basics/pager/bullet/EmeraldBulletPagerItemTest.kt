package br.com.stone.emeraldcomponents.basics.pager.bullet

import android.view.View
import br.com.stone.emeraldcomponents.basic.pager.bullet.EmeraldBulletPagerItem
import org.junit.Assert
import org.junit.Test

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldBulletPagerItemTest {

    @Test
    fun `Should instantiate correctly`() {
        val layoutId = 0
        val bindValues = { _: View -> }
        val item = EmeraldBulletPagerItem(layoutId, bindValues)

        Assert.assertEquals(layoutId, item.layoutId)
        Assert.assertEquals(bindValues, item.bindValues)
        Assert.assertEquals("", item.title)
    }
}