package br.com.stone.emeraldcomponents.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 30/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class DipTest {

    @Test
    fun `Should float convert to Dip`() {
        assertEquals(1.0f, 1f.toDip(RuntimeEnvironment.systemContext))
    }


    @Test
    fun `Should int convert to Dip`() {
        assertEquals(1, 1.toDip(RuntimeEnvironment.systemContext))
    }
}