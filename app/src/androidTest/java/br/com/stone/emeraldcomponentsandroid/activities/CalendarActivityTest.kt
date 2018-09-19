package br.com.stone.emeraldcomponentsandroid.activities


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.DatePicker
import br.com.stone.emeraldcomponents.extension.day
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponents.extension.month
import br.com.stone.emeraldcomponents.extension.year
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar


@RunWith(AndroidJUnit4::class)
class CalendarActivityTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(CalendarActivity::class.java)

    @Test
    fun shouldNavigateAmongMonthsAndChangeEmeraldTextDateTextValue() {
        val currentDate = Calendar.getInstance()

        onView(withId(R.id.emeraldImgLeftArrowDate))
                .perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(not(withText(currentDate.format("MMMM, yyyy")))))

        onView(withId(R.id.emeraldImgRightArrowDate))
                .perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(withText(currentDate.format("MMMM, yyyy"))))
    }

    @Test
    fun shouldOpenDatePickerDialogWhenClickOnEmeraldTextDate() {
        onView(withId(R.id.emeraldTextDate))
                .perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name))).check(matches(isDisplayed()))
    }

    @Test
    fun shouldSelectedOnDateWhenClickOnDatePickersDateItem() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, 0)
        cal.set(Calendar.DAY_OF_MONTH, 1)

        onView(withId(R.id.emeraldTextDate))
                .perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name)))
                .perform(PickerActions.setDate(cal.year(), cal.month() + 1, cal.day()))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.emeraldTextDate))
                .check(matches(withText(cal.format("MMMM, yyyy"))))
    }
}
