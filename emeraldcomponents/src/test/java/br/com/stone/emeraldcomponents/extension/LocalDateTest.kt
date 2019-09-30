package br.com.stone.emeraldcomponents.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.threeten.bp.LocalDate
import java.util.Calendar

/**
 * Created by renan.silva on 30/09/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class LocalDateTest {

    @Test
    fun `Should convert localDate to Calendar`() {
        val localDate = LocalDate.of(2019, 9, 30)
        assertEquals("2019-09-30", localDate.toCalendar().format("YYYY-MM-dd"))
    }

    @Test
    fun `Should convert Calendar to LocalDate`() {
        val calendar = Calendar.getInstance().apply {
            set(2019, 9, 30)
        }
        assertEquals("2019-09-30", calendar.toLocalDate().toString())
    }
}