package br.com.stone.emeraldcomponents.basics.calendar

import android.app.DatePickerDialog
import android.view.View
import androidx.fragment.app.FragmentActivity
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateSelector
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.month
import kotlinx.android.synthetic.main.widget_date.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowDialog
import java.util.Calendar

/**
 * Created on 02/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldDateSelectorTest {

    private lateinit var view: EmeraldDateSelector

    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        view = EmeraldDateSelector(activity)
    }

    @Test
    fun testInstanceWithContext() {
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet().build()
        val view = EmeraldDateSelector(activity, attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetDate() {
        val pattern = "MMMM, yyyy"
        val date = Calendar.getInstance()
        view.date = date
        assertEquals(date.format(pattern), view.emeraldTextDate.text)
    }

    @Test
    fun testSetDateChangedListener() {
        val mockList = mock(List::class.java)
        view.setDateChangedListener { mockList.size }
        view.emeraldImgRightArrowDate.performClick()
        verify(mockList).size
    }

    @Test
    fun testOnRightArrowClick() {
        val month = 10
        view.date.set(Calendar.DAY_OF_MONTH, 1)
        view.date.set(Calendar.MONTH, month)
        view.emeraldImgRightArrowDate.performClick()
        assertEquals(month + 1, view.date.month())
    }

    @Test
    fun testOnLeftArrowClick() {
        val month = 10
        view.date.set(Calendar.DAY_OF_MONTH, 1)
        view.date.set(Calendar.MONTH, month)
        view.emeraldImgLeftArrowDate.performClick()
        assertEquals(month - 1, view.date.month())
    }

    @Test
    fun testOnDateTitleClick() {
        view.emeraldTextDate.performClick()
        assertTrue(ShadowDialog.getLatestDialog() is DatePickerDialog)
    }

    @Test
    fun testShowArrows() {
        view.showArrows = true
        assertEquals(View.VISIBLE, view.emeraldImgLeftArrowDate.visibility)
        assertEquals(View.VISIBLE, view.emeraldImgRightArrowDate.visibility)
    }

    @Test
    fun testHideArrows() {
        view.showArrows = false
        assertEquals(View.GONE, view.emeraldImgLeftArrowDate.visibility)
        assertEquals(View.GONE, view.emeraldImgRightArrowDate.visibility)
    }
}