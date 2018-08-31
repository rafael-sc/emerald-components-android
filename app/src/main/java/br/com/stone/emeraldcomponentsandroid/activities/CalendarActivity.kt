package br.com.stone.emeraldcomponentsandroid.activities

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.widget.Toast
import br.com.stone.emeraldcomponents.basic.calendar.DateEvent
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponentsandroid.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val dates = listOf(
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 2),
                CalendarDay.from(2018, 4, 6),
                CalendarDay.from(2018, 4, 6),
                CalendarDay.from(2018, 4, 6),
                CalendarDay.from(2018, 4, 1),
                CalendarDay.from(2018, 4, 7),
                CalendarDay.from(2018, 4, 5),
                CalendarDay.from(2018, 4, 13),
                CalendarDay.from(2018, 4, 28)
        )

        calendar.highlightDays(R.color.colorAccent, dates.filter { it.day % 2 == 0 }.toSet())
        calendar.highlightDays(R.color.colorPrimary, dates.filter { it.day % 2 != 0 }.toSet())

        calendar.setOnDateChangedListener { _, selectedDate, _ ->
            eventList.selectEvent(selectedDate.calendar)
            Log.i("Calendar", "event " + eventList.selectedEvent?.toString())
            Log.i("Calendar", eventList.selectedEvent?.day().toString())
        }

        calendar.setOnMonthChangedListener { _, month ->
            Toast.makeText(this@CalendarActivity, "month changed $month",
                    Toast.LENGTH_SHORT).show()
        }

        val spannableString = SpannableString("string formatada")

        spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                6,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        dates.forEach {
            eventList.addEvent(
                    DateEvent(
                            it.calendar,
                            spannable = spannableString,
                            color = android.R.color.holo_green_light))
        }

        eventList.eventDateSelectedColor = android.R.color.holo_red_dark

        eventList.addEvent(
                DateEvent(
                        Calendar.getInstance(),
                        "String sem formato",
                        color = android.R.color.holo_red_light))

        eventList.itemClickListener = {
            Toast.makeText(this@CalendarActivity, "${this[0].day} clicked",
                    Toast.LENGTH_SHORT).show()
        }

        dateView.setDateChangedListener {
            Toast.makeText(this@CalendarActivity, it.format("dd 'de' MMMM, EEEE"),
                    Toast.LENGTH_SHORT).show()
        }

        dateView.setDateChangedListener {
            calendar.setCurrentDate(it)
        }
    }
}

