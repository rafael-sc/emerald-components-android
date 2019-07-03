package br.com.stone.emeraldcomponents.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.day(): Int = get(Calendar.DAY_OF_MONTH)

fun Calendar.month(): Int = get(Calendar.MONTH)

fun Calendar.year(): Int = get(Calendar.YEAR)

fun Calendar.format(pattern: String, locale: Locale = Locale("PT", "BR")): String =
        SimpleDateFormat(pattern, locale).format(this.time)

fun Calendar.toStartOfDay(): Calendar {
    val calendar = clone() as Calendar
    calendar.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar
}