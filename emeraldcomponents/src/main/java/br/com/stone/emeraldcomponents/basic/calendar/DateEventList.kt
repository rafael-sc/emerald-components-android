package br.com.stone.emeraldcomponents.basic.calendar

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.calendar.CalendarHelper.getGroupedEvents
import br.com.stone.emeraldcomponents.basic.calendar.CalendarHelper.sortDates
import br.com.stone.emeraldcomponents.basic.recyclerview.SlingAdapter
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.dimen
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.setUp
import kotlinx.android.synthetic.main.event_list_content_item.view.*
import kotlinx.android.synthetic.main.widget_event_list_item.view.*
import java.util.Calendar

class DateEventList : RecyclerView {

    private companion object {
        const val FONT_SIZE_RATE_DECREASE_PERCENTAGE = 0.50
        const val WEEK_PATTERN = "EEE"
    }

    internal val events: MutableList<MutableList<DateEvent>> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    var itemClickListener: List<DateEvent>.() -> Unit = {}
        set(value) {
            field = value
            (adapter as SlingAdapter<List<DateEvent>>).itemClick = value
        }

    var selectedEvent: Calendar? = null

    private lateinit var itemProperties: ItemProperties

    constructor(context: Context) : super(context) {
        //properties default values
        itemProperties = ItemProperties(
                dateSelectedColor = context.colorRes(R.color.emerald_event_date_selected),
                dateTextColor = context.colorRes(R.color.emerald_textview_default_color),
                dateTextColorWhenSelected = context.colorRes(android.R.color.white),
                eventTitleTextColor = context.colorRes(R.color.emerald_textview_default_color))
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    var eventDateSelectedColor = 0
        set(value) {
            field = context.colorRes(value)
            itemProperties.dateSelectedColor = field
        }

    var eventDateTextColor = 0
        set(value) {
            field = context.colorRes(value)
            itemProperties.dateTextColor = field
        }

    var eventDateTextColorWhenSelected = 0
        set(value) {
            field = context.colorRes(value)
            itemProperties.dateTextColorWhenSelected = field
        }

    var eventTitleTextColor = 0
        set(value) {
            field = context.colorRes(value)
            itemProperties.eventTitleTextColor = field
        }

    fun eventDateTextSize(unit: Int, size: Float) {
        itemProperties.dateTextSize = Pair(unit, size)
    }

    fun eventTitleTextSize(unit: Int, size: Float) {
        itemProperties.eventTitleTextSize = Pair(unit, size)
    }

    init {
        setUp(events, {
            R.layout.widget_event_list_item
        }, {
            applyStyle(this)

            emeraldEventDay.text = it.firstOrNull()?.day.toString()
            emeraldDayOfWeek.text = it.firstOrNull()?.date?.format(WEEK_PATTERN)

            emeraldEventList.setUp(it, { R.layout.event_list_content_item }, {
                with(this.emeraldEventTitle) {
                    text = it.spannable ?: (it.title ?: "")
                    setBackgroundColor(ContextCompat.getColor(context, it.color))

                    setTextColor(itemProperties.eventTitleTextColor)

                    itemProperties.eventTitleTextSize?.let {
                        setTextSize(it.first, it.second)
                    }
                }
            }, {
                itemClickListener(it)
            })

            emeraldDateContainer.isSelected = it.firstOrNull()?.day == selectedEvent?.day()

            if (emeraldDateContainer.isSelected) {
                emeraldDateContainer.background.colorFilter =
                        PorterDuffColorFilter(itemProperties.dateSelectedColor,
                                PorterDuff.Mode.SRC_ATOP)

                emeraldEventDay.setTextColor(itemProperties.dateTextColorWhenSelected)
                emeraldDayOfWeek.setTextColor(itemProperties.dateTextColorWhenSelected)
            }
        })

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        addItemDecoration(dividerItemDecoration)

        itemAnimator?.changeDuration = 0
    }

    private fun applyStyle(view: View) {
        with(view) {

            emeraldEventDay.setTextColor(itemProperties.dateTextColor)
            emeraldDayOfWeek.setTextColor(itemProperties.dateTextColor)

            itemProperties.dateTextSize?.let {
                emeraldEventDay.setTextSize(it.first, it.second)
                emeraldDayOfWeek.setTextSize(
                        it.first,
                        (it.second * FONT_SIZE_RATE_DECREASE_PERCENTAGE).toFloat())
            }
        }
    }

    private fun setAttributes(attrs: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.DateEventListView, 0, 0)

        with(context) {
            itemProperties = ItemProperties(
                    dateSelectedColor = args.getColor(
                            R.styleable.DateEventListView_eventDateSelectedColor,
                            colorRes(R.color.emerald_event_date_selected)),
                    dateTextColor = args.getColor(
                            R.styleable.DateEventListView_eventDateTextColor,
                            colorRes(R.color.emerald_textview_default_color)),
                    dateTextColorWhenSelected = args.getColor(
                            R.styleable.DateEventListView_eventDateTextColorWhenSelected,
                            colorRes(android.R.color.white)),
                    dateTextSize = Pair(TypedValue.COMPLEX_UNIT_PX, args.getDimension(
                            R.styleable.DateEventListView_eventDateTextSize,
                            dimen(R.dimen.emerald_large_text))),
                    eventTitleTextColor = args.getColor(
                            R.styleable.DateEventListView_eventTitleTextColor,
                            colorRes(R.color.emerald_textview_default_color)),
                    eventTitleTextSize = Pair(TypedValue.COMPLEX_UNIT_PX, args.getDimension(
                            R.styleable.DateEventListView_eventTitleTextSize,
                            dimen(R.dimen.emerald_large_text)))
            )
        }

        args.recycle()
    }

    @Suppress("UNCHECKED_CAST")
    fun setEvents(newEvents: List<DateEvent>) {
        events.clear()
        val eventsToAdd = getGroupedEvents(sortDates(newEvents)) as Collection<MutableList<DateEvent>>
        events.addAll(eventsToAdd)
        adapter?.notifyDataSetChanged()
    }

    fun addEvent(newEvent: DateEvent) {
        events.forEach {
            if (it.firstOrNull()?.day == newEvent.day) {
                it.add(newEvent)
                return
            }
        }
        events.add(mutableListOf(newEvent))
        events.sortBy { it.firstOrNull()?.date }
    }

    fun selectEvent(eventDay: Calendar) {
        val latestPosition = events.indexOfFirst { it.firstOrNull()?.day == selectedEvent?.day() }
        val position = events.indexOfFirst { it.firstOrNull()?.day == eventDay.day() }
        if (position == NO_POSITION) {
            adapter?.notifyItemChanged(latestPosition)
            selectedEvent = null
            return
        }

        selectedEvent = eventDay
        val smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }
        }.apply { targetPosition = position }

        layoutManager?.startSmoothScroll(smoothScroller)
        //notify item
        adapter?.notifyItemChanged(position)
        adapter?.notifyItemChanged(latestPosition)
    }

    private class ItemProperties(
            var dateSelectedColor: Int,
            var dateTextColor: Int,
            var dateTextColorWhenSelected: Int,
            var dateTextSize: Pair<Int, Float>? = null,
            var eventTitleTextColor: Int,
            var eventTitleTextSize: Pair<Int, Float>? = null
    )
}