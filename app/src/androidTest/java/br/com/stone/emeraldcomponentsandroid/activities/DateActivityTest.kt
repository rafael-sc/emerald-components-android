package br.com.stone.emeraldcomponentsandroid.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.appcompat.app.AppCompatActivity
import android.widget.DatePicker
import br.com.stone.emeraldcomponents.basic.calendar.EmeraldDateFilterOptions
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.month
import br.com.stone.emeraldcomponents.extension.year
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import java.util.Calendar

/**
 * Created by diegolucasb on 20/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class DateActivityTest: BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    val activityRule = activityTestRule<DateActivity>()

    private val formatPatternSelector by lazy {
        activityRule.activity.getString(R.string.emerald_date_selector_pattern_day)
    }

    private val formatPatternFilterTitle by lazy {
        activityRule.activity.getString(R.string.emerald_date_filter_pattern)
    }

    @Test
    fun shouldNavigateAmongMonthsAndChangeEmeraldTextDateTextValue() {
        val currentDate = Calendar.getInstance()

        onView(withId(R.id.emeraldImgLeftArrowDate))
                .perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(CoreMatchers.not(withText(currentDate.format(formatPatternSelector)))))

        onView(withId(R.id.emeraldImgRightArrowDate))
                .perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(withText(currentDate.format(formatPatternSelector))))

        screenShot("emerald-date-selector")
    }

    @Test
    fun shouldOpenDatePickerDialogWhenClickOnEmeraldTextDate() {
        onView(withId(R.id.emeraldTextDate))
                .perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name))).check(matches(isDisplayed()))

        screenShot("emerald-datepicker-selection")
    }

    @Test
    fun shouldSelectDateWhenClickOnDateTitleAndSelectDateFromDatePicker() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, 0)
        cal.set(Calendar.DAY_OF_MONTH, 1)

        onView(withId(R.id.emeraldTextDate))
                .perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name)))
                .perform(PickerActions.setDate(cal.year(), cal.month() + 1, cal.day()))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(withText(cal.format(formatPatternSelector))))

        screenShot("emerald-selected-date-from-picker")
    }

    @Test
    fun shouldOpenDatePickerDialogWhenClickOnPersonalizedDateRange() {
        onView(withText(equalTo(getString(R.string.emerald_date_filter_button))))
                .perform(click())

        val personalizedTitle = getString(EmeraldDateFilterOptions.PERSONALIZED.textResId)
        onView(withText(personalizedTitle)).perform(click())

        onView(withId(R.id.emeraldDateFilterStartDate)).perform(click())
        onView(withClassName(equalTo(DatePicker::class.java.name))).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.emeraldDateFilterEndDate)).perform(click())
        onView(withClassName(equalTo(DatePicker::class.java.name))).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).perform(click())

        screenShot("emerald-date-filter-personalized-dialog")
    }

    @Test
    fun shouldOpenOptionsWheClickOnPersonalizedFilter() {
        onView(withText(equalTo(getString(R.string.emerald_date_filter_button))))
                .perform(click())

        val personalizedTitle = getString(EmeraldDateFilterOptions.PERSONALIZED.textResId)
        onView(withText(personalizedTitle)).perform(click())

        onView(withId(R.id.emeraldTextDateLabel))
                .check(matches(withText(personalizedTitle)))

        onView(withId(R.id.emeraldTextDateRangeSubtitle)).check(matches(not(isDisplayed())))
        onView(withId(R.id.emeraldDateFilterStartDate)).check(matches(isDisplayed()))
        onView(withId(R.id.emeraldDateFilterEndDate)).check(matches(isDisplayed()))

        screenShot("emerald-date-filter-personalized")
    }

    @Test
    fun shouldChangeTitleAndDateTitleWhenSelectOnOptionFilterOnEmeraldDateFilter() {
        EmeraldDateFilterOptions.values().forEach {

            onView(withText(equalTo(getString(R.string.emerald_date_filter_button))))
                    .perform(click())

            val selectedTitle: String =
                    when(it) {
                        EmeraldDateFilterOptions.THIS_MONTH -> Calendar.getInstance().format("MMMM").capitalize()
                        EmeraldDateFilterOptions.LAST_MONTH -> {
                            Calendar.getInstance().let {
                                it.add(Calendar.MONTH, -1)
                                it
                            }.let {
                                it.format("MMMM").capitalize()
                            }
                        }
                        else -> getString(it.textResId)
                    }

            onView(withText(getString(it.textResId))).perform(click())

            onView(withId(R.id.emeraldTextDateLabel))
                    .check(matches(withText(selectedTitle)))

            val formattedSubtitleDate =
                    when(it) {
                        EmeraldDateFilterOptions.TODAY,
                        EmeraldDateFilterOptions.YESTERDAY -> { it.calculate(Calendar.getInstance()).second.format(formatPatternFilterTitle) }
                        else -> {
                            val range = it.calculate(Calendar.getInstance())

                            if(range.first == range.second) {
                                it.calculate(Calendar.getInstance()).second.format(formatPatternFilterTitle)
                            } else {
                                getString(R.string.emerald_date_filter_date_range,
                                        range.first.format(formatPatternFilterTitle),
                                        range.second.format(formatPatternFilterTitle))

                            }
                        }
                    }

            if(it != EmeraldDateFilterOptions.PERSONALIZED) {
                onView(withId(R.id.emeraldTextDateRangeSubtitle))
                        .check(matches(withText(formattedSubtitleDate)))
            } else {
                onView(withId(R.id.emeraldTextDateRangeSubtitle))
                        .check(matches(not(isDisplayed())))
            }

            screenShot("emerald-date-filter-click-{${getString(it.textResId)}}")
        }

    }

    private fun getString(resId: Int) = activityRule.activity.getString(resId)
    private fun getString(resId: Int, dateInitial: String, dataFinal: String) = activityRule.activity.getString(resId, dateInitial, dataFinal)

}