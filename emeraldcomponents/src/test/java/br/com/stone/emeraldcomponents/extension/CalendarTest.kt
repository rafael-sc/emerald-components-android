package br.com.stone.emeraldcomponents.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class CalendarTest {

    @Test
    fun testDay() {
        val testDay = 1
        val calendar = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, testDay) }
        assertEquals(testDay, calendar.day())
    }

    @Test
    fun testMonth() {
        val testMonth = Calendar.JANUARY
        val calendar = Calendar.getInstance().apply { set(Calendar.MONTH, testMonth) }
        assertEquals(testMonth, calendar.month())
    }

    @Test
    fun testFormat() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.DAY_OF_YEAR, 1)
        }
        assertEquals("Seg", calendar.format("EEE"))
    }
}