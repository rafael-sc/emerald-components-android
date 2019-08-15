package br.com.stone.emeraldcomponents.basics.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.recyclerview.AbstractAdapter
import br.com.stone.emeraldcomponents.basic.recyclerview.EndlessRecyclerViewManager
import br.com.stone.emeraldcomponents.basic.recyclerview.SlingAdapter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by renan.silva on 15/08/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
@Suppress("UNCHECKED_CAST")
class EndlessRecyclerViewManagerTest {

    lateinit var manager: EndlessRecyclerViewManager
    lateinit var recyclerView: RecyclerView

    @Before
    fun setup() {
        recyclerView = RecyclerView(ApplicationProvider.getApplicationContext())
        val adapter = SlingAdapter<String>({ 1 }, {})
        recyclerView.adapter = adapter
        manager = EndlessRecyclerViewManager(recyclerView, 0) {}
    }

    @Test
    fun `Should show loading be true when on last position and loading`() {
        (recyclerView.adapter as AbstractAdapter<String>).itemList = listOf("1", "2")
        manager.isLoading = true
        assertTrue(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should not show loading be false when on last position and not loading`() {
        (recyclerView.adapter as AbstractAdapter<String>).itemList = listOf("1", "2")
        manager.isLoading = false
        assertFalse(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should not show loading on last page`() {
        (recyclerView.adapter as AbstractAdapter<String>).itemList = listOf("1", "2")
        manager.isLoading = true
        manager.lastPageReached = true
        assertFalse(manager.shouldShowLoading(2))
    }

    @Test
    fun `Should add items`() {
        val adapter = recyclerView.adapter as AbstractAdapter<String>
        adapter.itemList = listOf()
        val expected = "test"
        manager.addItems(listOf(expected), adapter)
        assertEquals(expected, adapter.itemList.first())
    }
}