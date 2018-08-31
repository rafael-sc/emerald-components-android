package br.com.stone.emeraldcomponents.basics.calendar

import android.content.Context
import android.util.AttributeSet
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldCalendarView
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class EmeraldCalendarViewTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = RuntimeEnvironment.application
    }

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldCalendarView(context)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val view = EmeraldCalendarView(context, Mockito.mock(AttributeSet::class.java))
        Assert.assertNotNull(view)
    }

    @Test
    fun testHighlightDays() {
        val calendarView = spy(EmeraldCalendarView(context))
        calendarView.highlightDays(R.color.emerald_textview_default_color, setOf(CalendarDay.today()))
        verify(calendarView).addDecorator(any())
    }
}