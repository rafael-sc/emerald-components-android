package br.com.stone.emeraldcomponents.basics.calendar

import android.view.View
import androidx.fragment.app.FragmentActivity
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilter
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilterOptions
import br.com.stone.emeraldcomponents.extension.format
import kotlinx.android.synthetic.main.widget_date_filter.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.Calendar

/**
 * Created on 02/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldDateFilterTest {

    private lateinit var view: EmeraldDateFilter

    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        view = EmeraldDateFilter(activity)
    }

    @Test
    fun `Should create instance`() {
        Assert.assertNotNull(view)
    }

    @Test
    fun `Should create instance with attribute set`() {
        val attrs = Robolectric.buildAttributeSet().build()
        val view = EmeraldDateFilter(activity, attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun `Should set not personalized filter `() {
        val date = Calendar.getInstance()
        view.setFilter(EmeraldDateFilterOptions.TODAY)
        assertEquals(View.GONE, view.emeraldDateFilterExpandConstraint.visibility)
        assertEquals(View.VISIBLE, view.emeraldTextDateRangeSubtitle.visibility)
        assertEquals(EmeraldDateFilterOptions.TODAY, view.currentFilterType)
        assertEquals(view.startDate, date)
        assertEquals(view.endDate, date)
    }

    @Test
    fun `Should set personalized filter`() {
        val startDate = Calendar.getInstance()
        view.setFilter(EmeraldDateFilterOptions.PERSONALIZED)
        assertEquals(View.VISIBLE, view.emeraldDateFilterExpandConstraint.visibility)
        assertEquals(View.GONE, view.emeraldTextDateRangeSubtitle.visibility)
        assertEquals(EmeraldDateFilterOptions.PERSONALIZED, view.currentFilterType)
        assertEquals("Personalizado", view.emeraldTextDateLabel.text)
        assertEquals(startDate.format("dd/MM/yy"), view.emeraldDateFilterStartDate.text)
        assertEquals(startDate.format("dd/MM/yy"), view.emeraldDateFilterEndDate.text)
    }

    @Test
    fun `Should set custom filter`() {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
        view.setCustomFilter(startDate, endDate)
        assertEquals(View.VISIBLE, view.emeraldDateFilterExpandConstraint.visibility)
        assertEquals(View.GONE, view.emeraldTextDateRangeSubtitle.visibility)
        assertEquals(EmeraldDateFilterOptions.PERSONALIZED, view.currentFilterType)
        assertEquals("Personalizado", view.emeraldTextDateLabel.text)
        assertEquals(startDate.format("dd/MM/yy"), view.emeraldDateFilterStartDate.text)
        assertEquals(endDate.format("dd/MM/yy"), view.emeraldDateFilterEndDate.text)
    }

    @Test
    fun `Should validate date range`() {
        view.maxDaysRange = 2
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
        assertTrue(view.isValidDateRange(startDate, endDate))
    }

    @Test
    fun `Should not validate date range case endDate before startDate`() {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
        assertFalse(view.isValidDateRange(startDate, endDate))
    }

    @Test
    fun `Should not validate date range case range bigger then maxRange`() {
        view.maxDaysRange = 9
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 10) }
        assertFalse(view.isValidDateRange(startDate, endDate))
    }
}