package br.com.stone.emeraldcomponents.basics.pager

import android.util.AttributeSet
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.pager.EmeraldViewPager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldViewPagerTest {

    private lateinit var view: EmeraldViewPager

    @Before
    fun setup() {
        view = EmeraldViewPager(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Should not be null when instanced with context`() {
        val view = EmeraldViewPager(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun `Should not be null when instanced with context and attribute set`() {
        val view = EmeraldViewPager(ApplicationProvider.getApplicationContext(),
                Robolectric.buildAttributeSet().build())
        Assert.assertNotNull(view)
    }
}