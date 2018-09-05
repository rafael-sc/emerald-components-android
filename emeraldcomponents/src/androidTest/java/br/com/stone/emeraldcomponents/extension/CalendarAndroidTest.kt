package br.com.stone.emeraldcomponents.extension

import android.annotation.TargetApi
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import br.com.stone.emeraldcomponents.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar

/**
 * Created by victor.cruz on 05/09/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */

@RunWith(AndroidJUnit4::class)
@TargetApi(19)
class CalendarAndroidTest {

    private val context = InstrumentationRegistry.getContext()

    @Test
    fun testFormatWithDateSelectorDayPattern() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        Assert.assertEquals("02 de junho, sábado", calendar.format(context.getString(R.string.emerald_date_selector_pattern_day)))
    }

    @Test
    fun testFormatWithDateSelectorMonthPattern() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.MONTH, 5)
        }
        Assert.assertEquals("junho, 2018", calendar.format(context.getString(R.string.emerald_date_selector_pattern_month)))
    }

    @Test
    fun testFormatWithDateFilterPattern() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        Assert.assertEquals("02/06/18", calendar.format(context.getString(R.string.emerald_date_filter_pattern)))
    }

}