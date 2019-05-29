package br.com.stone.emeraldcomponents.basics.calendar

import android.util.AttributeSet
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldCalendarView
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.any
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmeraldCalendarViewTest {

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldCalendarView(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val view = EmeraldCalendarView(context, Robolectric.buildAttributeSet().build())
        Assert.assertNotNull(view)
    }

    @Test
    fun testHighlightDays() {
        val calendarView = spy(EmeraldCalendarView(ApplicationProvider.getApplicationContext()))
        calendarView.highlightDays(R.color.emerald_textview_default_color, setOf(CalendarDay.today()))
        verify(calendarView).addDecorator(any())
    }
}