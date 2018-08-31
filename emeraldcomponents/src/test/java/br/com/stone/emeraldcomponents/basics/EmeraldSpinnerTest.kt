package br.com.stone.emeraldcomponents.basics

import br.com.stone.emeraldcomponents.basic.EmeraldSpinner
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 19/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldSpinnerTest {

    private lateinit var spinner: EmeraldSpinner

    @Before
    fun setup() {
        spinner = EmeraldSpinner(RuntimeEnvironment.application)
    }

    @Test
    fun testSetItems() {
        val testList = listOf(
                "Description A",
                "Description B",
                "Description C",
                "Description D",
                "Description E"
        )
        spinner.setItems(testList)
        Assert.assertEquals(testList.size, spinner.adapter.count)
        Assert.assertEquals(testList.first(), spinner.adapter.getItem(0))
    }
}