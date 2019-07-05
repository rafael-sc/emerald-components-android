package br.com.stone.emeraldcomponents.extension

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import org.junit.Assert
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
    fun `Should get day`() {
        val testDay = 1
        val calendar = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, testDay) }
        assertEquals(testDay, calendar.day())
    }

    @Test
    fun `Should get month`() {
        val testMonth = Calendar.JANUARY
        val calendar = Calendar.getInstance().apply { set(Calendar.MONTH, testMonth) }
        assertEquals(testMonth, calendar.month())
    }

    @Test
    fun `Should get year`() {
        val testYear = 2000
        val calendar = Calendar.getInstance().apply { set(Calendar.YEAR, testYear) }
        assertEquals(testYear, calendar.year())
    }

    @Test
    fun `Should format to date selector day pattern`() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        assertEquals("02 de Junho, Sábado", calendar.format(context.getString(R.string.emerald_date_selector_pattern_day)))
    }

    @Test
    fun `Should format to date selector month pattern`() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.MONTH, 5)
        }
        assertEquals("Junho, 2018", calendar.format(context.getString(R.string.emerald_date_selector_pattern_month)))
    }

    @Test
    fun `Should format with date filter pattern`() {
        val calendar = Calendar.getInstance().apply {
            set(2018, 5, 2)
        }
        assertEquals("02/06/18", calendar.format(context.getString(R.string.emerald_date_filter_pattern)))
    }

    @Test
    fun `Should set calendar to start of day`() {
        val testCalendar = Calendar.getInstance()
        testCalendar.set(2000, 1, 1, 23, 59, 59)
        val resultCalendar = testCalendar.toStartOfDay()
        assertEquals(resultCalendar.get(Calendar.HOUR_OF_DAY), 0)
        assertEquals(resultCalendar.get(Calendar.MINUTE), 0)
        assertEquals(resultCalendar.get(Calendar.SECOND), 0)
        Assert.assertTrue(testCalendar.timeInMillis > resultCalendar.timeInMillis)
    }
}