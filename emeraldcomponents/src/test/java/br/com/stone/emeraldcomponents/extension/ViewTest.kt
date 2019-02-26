package br.com.stone.emeraldcomponents.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 19/04/2018.
 */
@RunWith(RobolectricTestRunner::class)
class ViewTest {

    @Test
    fun testViewGetActivity() {
        val activity = Robolectric.buildActivity(androidx.fragment.app.FragmentActivity::class.java).create().get()
        val view = View(activity)
        assertEquals(activity, view.getActivity())
    }

    @Test(expected = Exception::class)
    fun testViewGetActivityWithoutFragmentActivity() {
        val view = View(RuntimeEnvironment.application)
        view.getActivity()
    }

    @Test
    fun testRecyclerViewSetUp() {
        val recyclerView = RecyclerView(RuntimeEnvironment.application)
        val adapter = recyclerView.setUp(listOf(""), { 0 }, {})
        assertEquals(adapter.itemCount, 1)
    }
}