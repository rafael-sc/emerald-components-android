package br.com.stone.emeraldcomponents.basics.calendar

import br.com.stone.emeraldcomponents.basic.calendar.CalendarHelper
import br.com.stone.emeraldcomponents.basic.calendar.DateEvent
import br.com.stone.emeraldcomponents.extension.day
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class CalendarHelperTest {

    @Test
    fun testArrangeDates() {
        val events = listOf(
                DateEvent(Calendar.getInstance()
                        .apply {
                            set(Calendar.DAY_OF_MONTH, 2)
                            set(Calendar.MONTH, 2)
                        }, "test"),
                DateEvent(Calendar.getInstance()
                        .apply {
                            set(Calendar.DAY_OF_MONTH, 3)
                            set(Calendar.MONTH, 3)
                        }, "test"),
                DateEvent(Calendar.getInstance()
                        .apply {
                            set(Calendar.DAY_OF_MONTH, 10)
                            set(Calendar.MONTH, 1)
                        }, "test"),
                DateEvent(Calendar.getInstance()
                        .apply {
                            set(Calendar.DAY_OF_MONTH, 20)
                            set(Calendar.MONTH, 1)
                        }, "test")
        )

        val arrangedEvents = CalendarHelper.sortDates(events)

        Assert.assertEquals(10, arrangedEvents[0].day)
        Assert.assertEquals(20, arrangedEvents[1].day)

        Assert.assertEquals(2, arrangedEvents[2].month)
        Assert.assertEquals(3, arrangedEvents[3].month)
    }

    @Test
    fun testGetGroupedEvents() {
        val eventList = listOf(
                DateEvent(Calendar.getInstance(), "test"),
                DateEvent(Calendar.getInstance().apply { this.set(Calendar.DAY_OF_MONTH, day() + 1) }, "teste"),
                DateEvent(Calendar.getInstance(), "test2")
        )
        val arrangedEvents = CalendarHelper.getGroupedEvents(eventList)
        assertEquals(2, arrangedEvents.size)
        assertEquals(2, arrangedEvents[0].size)
        assertEquals(1, arrangedEvents[1].size)
        assertEquals(arrangedEvents[0][0].date.day(), arrangedEvents[0][1].date.day())
    }
}