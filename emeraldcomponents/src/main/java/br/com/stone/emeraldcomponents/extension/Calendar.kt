package br.com.stone.emeraldcomponents.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.day(): Int = get(Calendar.DAY_OF_MONTH)

fun Calendar.month(): Int = get(Calendar.MONTH)

fun Calendar.year(): Int = get(Calendar.YEAR)

fun Calendar.format(pattern: String, locale: Locale = Locale("PT", "BR")): String =
        SimpleDateFormat(pattern, locale).format(this.time)