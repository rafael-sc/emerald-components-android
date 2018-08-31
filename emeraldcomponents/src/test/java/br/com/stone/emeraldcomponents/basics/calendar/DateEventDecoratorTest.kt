package br.com.stone.emeraldcomponents.basics.calendar

import br.com.stone.emeraldcomponents.basic.calendar.DateEventDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewFacade
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DateEventDecoratorTest {

    private lateinit var day: CalendarDay

    private lateinit var eventDecorator: DateEventDecorator

    @Before
    fun setup() {
        day = CalendarDay.today()
        eventDecorator = DateEventDecorator(8f, 0, setOf(day))
    }

    @Test
    fun testShouldDecorate() {
        Assert.assertTrue(eventDecorator.shouldDecorate(day))
        Assert.assertFalse(eventDecorator.shouldDecorate(CalendarDay.from(1, 1, 1)))
    }

    @Test
    fun testDecorate() {
        val view = mock(DayViewFacade::class.java)
        eventDecorator.decorate(view)
        verify(view).addSpan(any())
    }
}