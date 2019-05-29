package br.com.stone.emeraldcomponents.extension

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.Calendar

@RunWith(RobolectricTestRunner::class)
class CalendarTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

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
    fun testYear() {
        val testYear = 2000
        val calendar = Calendar.getInstance().apply { set(Calendar.YEAR, testYear) }
        assertEquals(testYear, calendar.year())
    }

    @Test
    fun testFormatWithDateSelectorDayPattern() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        assertEquals("02 de Junho, Sábado", calendar.format(context.getString(R.string.emerald_date_selector_pattern_day)))
    }

    @Test
    fun testFormatWithDateSelectorMonthPattern() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.MONTH, 5)
        }
        assertEquals("Junho, 2018", calendar.format(context.getString(R.string.emerald_date_selector_pattern_month)))
    }

    @Test
    fun testFormatWithDateFilterPattern() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        assertEquals("02/06/18", calendar.format(context.getString(R.string.emerald_date_filter_pattern)))
    }
}