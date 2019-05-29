package br.com.stone.emeraldcomponents.basics.calendar

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.calendar.DateEvent
import br.com.stone.emeraldcomponents.basic.calendar.DateEventList
import br.com.stone.emeraldcomponents.basic.recyclerview.SlingAdapter
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponents.extension.day
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.Calendar

@RunWith(RobolectricTestRunner::class)
class DateEventListTest {

    private lateinit var eventList: DateEventList

    @Before
    fun setup() {
        eventList = DateEventList(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testNotNull() {
        Assert.assertNotNull(eventList)
    }

    @Test
    fun testEventDateSelectedColorWasSet() {
        eventList.eventDateSelectedColor = R.color.emerald_textview_default_color
        assertEquals(eventList.eventDateSelectedColor,
                eventList.context.colorRes(R.color.emerald_textview_default_color))
    }

    @Test
    fun testEventDateTextColorWasSet() {
        eventList.eventDateTextColor = R.color.emerald_textview_default_color
        assertEquals(eventList.eventDateTextColor,
                eventList.context.colorRes(R.color.emerald_textview_default_color))
    }

    @Test
    fun testEventDateTextColorWhenSelectedWasSet() {
        eventList.eventDateTextColorWhenSelected = R.color.emerald_textview_default_color
        assertEquals(eventList.eventDateTextColorWhenSelected,
                eventList.context.colorRes(R.color.emerald_textview_default_color))
    }

    @Test
    fun testEventTitleTextColorWasSet() {
        eventList.eventTitleTextColor = R.color.emerald_textview_default_color
        assertEquals(eventList.eventTitleTextColor,
                eventList.context.colorRes(R.color.emerald_textview_default_color))
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.eventDateSelectedColor, "@android:color/white")
                .addAttribute(R.attr.eventDateTextColor, "@android:color/white")
                .addAttribute(R.attr.eventDateTextColorWhenSelected, "@android:color/white")
                .addAttribute(R.attr.eventDateTextSize, "12sp")
                .addAttribute(R.attr.eventTitleTextColor, "@android:color/white")
                .addAttribute(R.attr.eventTitleTextSize, "12sp")
                .addAttribute(R.attr.color, "@android:color/white")
                .build()

        val view = DateEventList(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetEvents() {
        val testEvent = DateEvent(Calendar.getInstance(), "test")
        val replacementTestEvent = DateEvent(Calendar.getInstance(), "test")

        eventList.addEvent(testEvent)
        assertEquals(testEvent, eventList.events[0][0])
        eventList.setEvents(listOf(replacementTestEvent))
        assertEquals(replacementTestEvent, eventList.events[0][0])
    }

    @Test
    fun testSetEventsTitleSpannable() {
        val spannableString = SpannableString("string formatada")

        spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                6,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE )

        val testEvent = DateEvent(Calendar.getInstance(), spannable = spannableString)
        eventList.addEvent(testEvent)
        assertEquals(testEvent, eventList.events[0][0])
    }

    @Test
    fun testAddEvent() {
        val testEvent = DateEvent(Calendar.getInstance(), "test")
        eventList.addEvent(testEvent)
        assertEquals(testEvent, eventList.events[0][0])
        val testEvent2 = DateEvent(Calendar.getInstance(), "test")
        eventList.addEvent(testEvent2)
        assertEquals(testEvent2, eventList.events[0][1])
        val testEvent3 = DateEvent(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, day() + 1) }, "test")
        eventList.addEvent(testEvent3)
        assertEquals(testEvent3, eventList.events[1][0])
    }

    @Test
    fun testSelectEvent() {
        val testEvent = DateEvent(Calendar.getInstance(), "test")
        eventList.addEvent(testEvent)
        eventList.selectEvent(testEvent.date)
        assertEquals(testEvent.date, eventList.selectedEvent)

        val testEvent2 = DateEvent(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, day() + 1) }, "test")
        eventList.addEvent(testEvent2)
        eventList.selectEvent(testEvent2.date)
        assertEquals(testEvent2.date, eventList.selectedEvent)
    }

    @Test
    fun testSelectEventInvalid() {
        val testEvent = DateEvent(Calendar.getInstance(), "test")
        eventList.selectEvent(testEvent.date)
        assertEquals(null, eventList.selectedEvent)
    }

    @Test
    fun testSelectEventInvalidAfterSelectingAValid() {
        val testEvent = DateEvent(Calendar.getInstance(), "test")
        eventList.addEvent(testEvent)
        eventList.selectEvent(testEvent.date)
        assertEquals(testEvent.date, eventList.selectedEvent)

        val testInvalidEvent = DateEvent(Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, day() + 1) }, "test")
        eventList.selectEvent(testInvalidEvent.date)
        assertEquals(null, eventList.selectedEvent)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testAdapterClickEvent() {
        eventList.itemClickListener = { }
        assertNotNull((eventList.adapter as SlingAdapter<DateEvent>).itemClick)
    }
}