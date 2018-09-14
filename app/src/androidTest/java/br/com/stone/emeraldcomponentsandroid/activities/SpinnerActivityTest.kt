package br.com.stone.emeraldcomponentsandroid.activities

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import br.com.stone.emeraldcomponentsandroid.CustomMatcher
import br.com.stone.emeraldcomponentsandroid.CustomMatcher.childAtPosition
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by diegolucasb on 14/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
@RunWith(AndroidJUnit4::class)
class SpinnerActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(SpinnerActivity::class.java)

    @Test
    fun shouldAutoCompleteValidateTypedValue() {
        val autoCompleteView = onView(allOf(
                withId(R.id.emeraldAutoCompleteView),
                childAtPosition(childAtPosition(withId(R.id.autoCompleteView),
                        0),
                        0),
                isDisplayed()))

        autoCompleteView.perform(click())

        val chosenValue = "100 - Aaaaaaaaa"
        autoCompleteView.perform(replaceText(chosenValue), closeSoftKeyboard())
                .check(matches(withText(chosenValue)))
    }

    @Test
    fun shouldSpinnerMatchTestWhenUserClickInOneOption() {
        onView(allOf<View>(
                withId(R.id.emeraldSpinner),
                childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                                0),
                isDisplayed()))
                .perform(click())

        val appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(`is`<String>("android.widget.PopupWindow\$PopupBackgroundView")),
                        0))
                .atPosition(1)
        appCompatTextView.perform(click())

        val expectedOption = "Option 2"
        onView(allOf<View>(
                withId(android.R.id.text1), withText(expectedOption),
                CustomMatcher.childAtPosition(allOf<View>(
                        withId(R.id.emeraldSpinner),
                        childAtPosition(
                                IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                0)),
                                0),
                        isDisplayed()))
        .check(matches(withText(expectedOption)))
    }



}