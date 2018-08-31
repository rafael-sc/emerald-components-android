package br.com.stone.emeraldcomponents.basic.calendar

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import br.com.stone.emeraldcomponents.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.Calendar
import java.util.Locale

class EmeraldCalendarView : MaterialCalendarView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        topbarVisible = false
        setWeekDayFormatter {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_WEEK, it)
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale("PT", "BR"))
        }
    }

    fun highlightDays(color: Int, dates: Set<CalendarDay>) {
        val typedDimen = TypedValue()
        resources.getValue(R.dimen.emerald_calendar_radius, typedDimen, true)
        addDecorator(
                DateEventDecorator(
                        typedDimen.float,
                        ContextCompat.getColor(context, color),
                        dates))
    }
}