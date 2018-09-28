package br.com.stone.emeraldcomponentsandroid.activities


import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test


/**
 * Created by diegolucasb on 19/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class CalendarActivityTest: BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    var activityRule = activityTestRule<CalendarActivity>()

    @Test
    fun shouldEventListAndFirstItemBeDisplayed() {
        onView(withId(R.id.eventList))
                .check(matches(isDisplayed()))

        onView(allOf(
                withId(R.id.emeraldEventDay),
                withText("1")))
                .perform(click())
                .check(matches(isDisplayed()))

        screenShot("emerald-event-list-top")
    }

    @Test
    fun shouldEventListScrollAndClickOnLastPosition() {
        onView(withId(R.id.eventList))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))
                .check(matches(isDisplayed()))

        onData(anything())
                .inAdapterView(withId(R.id.emeraldEventList))
                .atPosition(6)
                .onChildView(allOf(
                        withId(R.id.emeraldEventTitle),
                        withText("String sem formato"),
                        isDisplayed())
                )

        screenShot("emerald-event-list-bottom")
    }
}
