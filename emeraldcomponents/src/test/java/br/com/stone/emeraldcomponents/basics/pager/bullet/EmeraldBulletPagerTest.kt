package br.com.stone.emeraldcomponents.basics.pager.bullet

import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.pager.bullet.EmeraldBulletPager
import br.com.stone.emeraldcomponents.basic.pager.bullet.EmeraldBulletPagerItem
import br.com.stone.emeraldcomponents.extension.toDip
import kotlinx.android.synthetic.main.widget_tab_pager.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldBulletPagerTest {

    private lateinit var pager: EmeraldBulletPager
    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        activity.setTheme(R.style.Base_Theme_AppCompat)
        pager = EmeraldBulletPager(activity)
    }

    @Test
    fun `Should not be null when instanced with context`() {
        Assert.assertNotNull(pager)
    }

    @Test
    fun `Should not be null when instanced with context and attribute set`() {
        val pager = EmeraldBulletPager(activity, Mockito.mock(AttributeSet::class.java))
        Assert.assertNotNull(pager)
    }

    @Test
    fun `Should set tabs when set adapter is called`() {
        val item = EmeraldBulletPagerItem(R.layout.widget_autocomplete) { }
        pager.setAdapter(listOf(item))
        assertEquals(1, pager.emeraldTabLayout.tabCount)
    }


    @Test
    fun `Should change page width when set adapter is called with width`() {
        val testWidth = 0.1f
        val item = EmeraldBulletPagerItem(R.layout.widget_autocomplete) { }
        pager.setAdapter(listOf(item), testWidth)
        assertEquals(testWidth, pager.viewPager.adapter?.getPageWidth(0))
    }

    @Test
    fun `Should change page margin when set adapter is called with margin`() {
        val testMargin = 1
        val item = EmeraldBulletPagerItem(R.layout.widget_autocomplete) { }
        pager.setAdapter(listOf(item), pageMargin = testMargin)
        assertEquals(testMargin.toDip(activity), pager.viewPager.pageMargin)
    }
}