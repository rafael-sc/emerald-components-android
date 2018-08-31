package br.com.stone.emeraldcomponents.basic.calendar

import br.com.stone.emeraldcomponents.extension.day

object CalendarHelper {

    fun sortDates(events: List<DateEvent>): List<DateEvent> {
        return events.sortedBy { it.date }
    }

    fun getGroupedEvents(events: List<DateEvent>): List<List<DateEvent>> {
        val compositeDateEvent = mutableListOf<List<DateEvent>>()
        val groups = events.groupBy { it.date.day() }
        groups.forEach {
            compositeDateEvent.add(sortDates(it.value))
        }

        return compositeDateEvent
    }
}