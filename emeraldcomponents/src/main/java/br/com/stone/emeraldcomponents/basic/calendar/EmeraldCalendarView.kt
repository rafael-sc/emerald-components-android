package br.com.stone.emeraldcomponents.basic.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import org.threeten.bp.format.TextStyle
import java.util.Locale

class EmeraldCalendarView : MaterialCalendarView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        topbarVisible = false
        setWeekDayFormatter {
            it.getDisplayName(TextStyle.SHORT, Locale("PT", "BR"))
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