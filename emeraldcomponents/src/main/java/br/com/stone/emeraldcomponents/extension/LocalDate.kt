package br.com.stone.emeraldcomponents.extension

import org.threeten.bp.LocalDate
import java.util.Calendar


/**
 * Created by renan.silva on 27/09/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
fun LocalDate.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(year, monthValue - 1, dayOfMonth)
    return calendar
}

fun Calendar.toLocalDate(): LocalDate {
    return LocalDate.of(year(), month(), day())
}