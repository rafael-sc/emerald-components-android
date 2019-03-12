package br.com.stone.emeraldcomponents.basics.calendar

import androidx.fragment.app.FragmentActivity
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilterOptions
import br.com.stone.emeraldcomponents.extension.day
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created on 02/08/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class EmeraldDateFilterOptionsTest {

    private lateinit var day: Calendar

    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        day = Calendar.getInstance()
        day.time = SimpleDateFormat("yyyy-MM-dd").parse("2018-08-28")
    }

    @Test
    fun testStringToday() {
        val string = activity.getString(EmeraldDateFilterOptions.TODAY.textResId)
        assertEquals(string, "Hoje")
    }

    @Test
    fun testStringYesterday() {
        val string = activity.getString(EmeraldDateFilterOptions.YESTERDAY.textResId)
        assertEquals(string, "Ontem")
    }

    @Test
    fun testStringTodayLast7Days() {
        val string = activity.getString(EmeraldDateFilterOptions.LAST_SEVEN_DAYS.textResId)
        assertEquals(string, "Últimos 7 dias")
    }

    @Test
    fun testStringLastThirtyDays() {
        val string = activity.getString(EmeraldDateFilterOptions.LAST_THIRTY_DAYS.textResId)
        assertEquals(string, "Últimos 30 dias")
    }

    @Test
    fun testStringThisMonth() {
        val string = activity.getString(EmeraldDateFilterOptions.THIS_MONTH.textResId)
        assertEquals(string, "Este mês")
    }

    @Test
    fun testStringLastMonth() {
        val string = activity.getString(EmeraldDateFilterOptions.LAST_MONTH.textResId)
        assertEquals(string, "Mês passado")
    }

    @Test
    fun testStringPersonalized() {
        val string = activity.getString(EmeraldDateFilterOptions.PERSONALIZED.textResId)
        assertEquals(string, "Personalizado")
    }

    @Test
    fun testCalculateToday() {
        val (start, end) = EmeraldDateFilterOptions.TODAY.calculate(day)
        assertEquals(start.day(), 28)
        assertEquals(end.day(), 28)
    }

    @Test
    fun testCalculateYesterday() {
        val (start, end) = EmeraldDateFilterOptions.YESTERDAY.calculate(day)
        assertEquals(start.day(), 27)
        assertEquals(end.day(), 27)
    }

    @Test
    fun testCalculateLast7Days() {
        val (start, end) = EmeraldDateFilterOptions.LAST_SEVEN_DAYS.calculate(day)
        assertEquals(start.day(), 22)
        assertEquals(end.day(), 28)
    }

    @Test
    fun testCalculateLast30Days() {
        val (start, end) = EmeraldDateFilterOptions.LAST_THIRTY_DAYS.calculate(day)
        assertEquals(start.day(), 30)
        assertEquals(end.day(), 28)
    }

    @Test
    fun testCalculateThisMonth() {
        val (start, end) = EmeraldDateFilterOptions.THIS_MONTH.calculate(day)
        assertEquals(start.day(), 1)
        assertEquals(end.day(), 28)
    }

    @Test
    fun testCalculateLastMonth() {
        val (start, end) = EmeraldDateFilterOptions.LAST_MONTH.calculate(day)
        assertEquals(start.day(), 1)
        assertEquals(end.day(), 31)
    }

    @Test
    fun testCalculatePersonalized() {
        val (start, end) = EmeraldDateFilterOptions.PERSONALIZED.calculate(day)
        assertEquals(start.day(), 28)
        assertEquals(end.day(), 28)
    }
}