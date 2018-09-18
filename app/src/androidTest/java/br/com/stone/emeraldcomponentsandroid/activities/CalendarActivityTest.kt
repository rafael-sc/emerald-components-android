package br.com.stone.emeraldcomponentsandroid.activities


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.stone.emeraldcomponents.extension.format
import br.com.stone.emeraldcomponentsandroid.R
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
    fun shouldNavigateBetweenMonths() {

        val currentDate = Calendar.getInstance()

        onView(withId(R.id.emeraldImgLeftArrowDate)).perform(click())

        onView(withId(R.id.emeraldTextDate)).check(matches(not(withText(currentDate.format("MMMM, yyyy")))))

        onView(withId(R.id.emeraldImgRightArrowDate)).perform(click())

        onView(withId(R.id.emeraldTextDate)).check(matches(withText(currentDate.format("MMMM, yyyy"))))

    }


}
