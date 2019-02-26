package br.com.stone.emeraldcomponents.basics.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.recyclerview.AbstractAdapter
import br.com.stone.emeraldcomponents.basic.recyclerview.SlingAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class EmeraldAdapterTest {

    private lateinit var adapter: SlingAdapter<String>

    private lateinit var mockObject: List<*>

    @Before
    fun setup() {
        mockObject = mock(List::class.java)
        adapter = SlingAdapter({ R.layout.test_layout }, { mockObject.size }, { mockObject.indices })
        adapter.itemList = listOf("")
    }

    @Test
    fun testOnBindViewHolder() {
        adapter.onBindViewHolder(AbstractAdapter.Holder(View(RuntimeEnvironment.application)), 0)
        verify(mockObject).size
    }

    @Test
    fun testOnItemClick() {
        val recyclerView = RecyclerView(RuntimeEnvironment.application)
        recyclerView.adapter = adapter
        val viewHolder = adapter.onCreateViewHolder(
                FrameLayout(RuntimeEnvironment.application), R.layout.test_layout)
        adapter.onBindViewHolder(viewHolder, 0)
        viewHolder.itemView.performClick()
    }

    @Test
    fun testGetItemViewType() {
        assertEquals(R.layout.test_layout, adapter.getItemViewType(0))
    }
}