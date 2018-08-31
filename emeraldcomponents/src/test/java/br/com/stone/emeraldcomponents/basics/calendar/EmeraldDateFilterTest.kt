package br.com.stone.emeraldcomponents.basics.calendar

import android.support.v4.app.FragmentActivity
import android.view.View
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilter
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilterOptions
import kotlinx.android.synthetic.main.widget_date_filter.view.*
import org.junit.Assert
import org.junit.Assert.assertEquals
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
    fun testInstanceWithContext() {
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetFilterNotPersonalized() {
        view.setFilter(EmeraldDateFilterOptions.YESTERDAY)
        assertEquals(view.emeraldDateFilterExpandConstraint.visibility, View.GONE)
        assertEquals(view.emeraldTextDateRangeSubtitle.visibility, View.VISIBLE)
    }

    @Test
    fun testSetFilterPersonalized() {
        view.setFilter(EmeraldDateFilterOptions.PERSONALIZED)
        assertEquals(view.emeraldTextDateLabel.text, view.context.getString(EmeraldDateFilterOptions.PERSONALIZED.textResId))
        assertEquals(view.emeraldDateFilterExpandConstraint.visibility, View.VISIBLE)
        assertEquals(view.emeraldTextDateRangeSubtitle.visibility, View.GONE)
    }

    @Test
    fun testSetCustomFilter() {
        val date = Calendar.getInstance()
        view.setCustomFilter(date, date)
        assertEquals(view.startDate, date)
        assertEquals(view.endDate, date)
        assertEquals(view.emeraldTextDateLabel.text, view.context.getString(EmeraldDateFilterOptions.PERSONALIZED.textResId))
        assertEquals(view.emeraldDateFilterExpandConstraint.visibility, View.VISIBLE)
        assertEquals(view.emeraldTextDateRangeSubtitle.visibility, View.GONE)
    }
}