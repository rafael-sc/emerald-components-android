package br.com.stone.emeraldcomponents.basics.label

import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.label.EmeraldLabelType
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by renan.silva on 24/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldLabelTypeTest {

    @Test
    fun testError() {
        assertEquals(R.color.emerald_error, EmeraldLabelType.ERROR.color)
    }
    @Test
    fun testWarning() {
        assertEquals(R.color.emerald_attention, EmeraldLabelType.WARNING.color)
    }
    @Test
    fun testNeutral() {
        assertEquals(R.color.emerald_white_4, EmeraldLabelType.NEUTRAL.color)
    }

    @Test
    fun testSuccess() {
        assertEquals(R.color.emerald_success, EmeraldLabelType.SUCCESS.color)
    }
    @Test
    fun testInfo() {
        assertEquals(R.color.emerald_secondary, EmeraldLabelType.INFO.color)
    }
}