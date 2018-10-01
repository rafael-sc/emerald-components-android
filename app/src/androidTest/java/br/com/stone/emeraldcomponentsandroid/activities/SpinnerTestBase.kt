package br.com.stone.emeraldcomponentsandroid.activities

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withSpinnerText
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test


/**
 * Created by diegolucasb on 14/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class SpinnerTestBase: BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    val activityRule = activityTestRule<SpinnerActivity>()

    @Test
    fun shouldAutoCompleteValidateTypedValue() {
        val autoCompleteView = onView(allOf(
                ViewMatchers.isDescendantOfA(withId(R.id.emeraldAutoComplete)),
                ViewMatchers.isAssignableFrom(EditText::class.java)))

        screenShot("before-type")

        val query = "100 - Aaaaaaaaa"
        autoCompleteView.perform(click())
        autoCompleteView.perform(replaceText("100"), closeSoftKeyboard())

        screenShot("after-type")

        onView(withText(query)).inRoot(withDecorView(not(`is`(activity.window.decorView))))
                .perform(click())

        autoCompleteView.check(matches(withText(query)))

        screenShot("finished-autocomplete")
    }

    @Test
    fun shouldSpinnerMatchTestWhenUserClickInOneOption() {
        onView((withId(R.id.emeraldSpinner)))
                .perform(click())

        screenShot("initial-state")

        val expectedOption = "Option 2"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(expectedOption))).perform(click())
        onView((withId(R.id.emeraldSpinner))).check(matches(withSpinnerText(expectedOption)))

        screenShot("finished-spinner")
    }
}