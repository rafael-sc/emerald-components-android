package br.com.stone.emeraldcomponents.basic.calendar

import android.text.Spannable
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.month
import java.util.Calendar

data class DateEvent(val date: Calendar,
                     val title: String? = null,
                     val spannable: Spannable? = null,
                     val color: Int = android.R.color.transparent) {

    val day = date.day()
    val month = date.month()
}