package br.com.stone.emeraldcomponents.basic.calendar

import br.com.stone.emeraldcomponents.R
import java.util.Calendar

/**
 * Created on 27/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */

enum class EmeraldDateFilterOptions(
        val textResId: Int, val calculate: (today: Calendar) -> Pair<Calendar, Calendar> ) {

    TODAY(R.string.emerald_date_filter_option_today, { Pair(it, it) }),

    YESTERDAY(R.string.emerald_date_filter_option_yesterday, {
        val date = it.apply { set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) - 1) }
        Pair(date, date)
    }),

    LAST_SEVEN_DAYS(R.string.emerald_date_filter_option_last_7_days, {
        val today = it.clone() as Calendar
        it.apply {
            set(Calendar.WEEK_OF_YEAR, get(Calendar.WEEK_OF_YEAR) - 1)
            set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) + 1)
        }
        Pair(it, today)
    }),

    LAST_THIRTY_DAYS(R.string.emerald_date_filter_option_last_30_days, {
        val today = it.clone() as Calendar
        it.apply { set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) - 29) }
        Pair(it, today)
    }),

    THIS_MONTH(R.string.emerald_date_filter_option_this_month, {
        val today = it.clone() as Calendar
        it.apply { set(Calendar.DAY_OF_MONTH, 1) }
        Pair(it, today)
    }),

    LAST_MONTH(R.string.emerald_date_filter_option_last_month, {
        val today = it.clone() as Calendar
        it.apply {
            set(Calendar.MONTH, get(Calendar.MONTH) - 1)
            set(Calendar.DAY_OF_MONTH, 1)
        }
        today.apply {
            set(Calendar.MONTH, get(Calendar.MONTH) - 1)
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
        }
        Pair(it, today)
    }),

    PERSONALIZED(R.string.emerald_date_filter_option_personalized, { Pair(it, it) })
}