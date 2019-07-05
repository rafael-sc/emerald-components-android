package br.com.stone.emeraldcomponents.basics.recyclerview

import android.view.View
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.recyclerview.AbstractAdapter
import kotlinx.android.synthetic.main.test_layout.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AbstractAdapterTest {

    class AbstractAdapterImpl : AbstractAdapter<String>()

    private lateinit var adapter: AbstractAdapter<String>

    private lateinit var mockObject: List<*>

    @Before
    fun setup() {
        mockObject = Mockito.mock(List::class.java)
        adapter = AbstractAdapterImpl()
        adapter.itemList = listOf("")
    }

    @Test
    fun testGetItemCount() {
        Assert.assertEquals(1, adapter.itemCount)
    }

    @Test
    fun testOnCreateViewHolder() {
        val viewHolder = adapter.onCreateViewHolder(FrameLayout(ApplicationProvider.getApplicationContext()), R.layout.test_layout)
        Assert.assertNotNull(viewHolder.itemView.test_text)
    }

    @Test
    fun testOnBindViewHolder() {
        adapter.onBindViewHolder(AbstractAdapter.Holder(View(ApplicationProvider.getApplicationContext())), 0)
    }
}