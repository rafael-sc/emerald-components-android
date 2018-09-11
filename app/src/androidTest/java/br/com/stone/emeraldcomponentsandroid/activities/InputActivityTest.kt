package br.com.stone.emeraldcomponentsandroid.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.EditText
import br.com.stone.emeraldcomponentsandroid.CustomMatcher
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by lucasdiego on 11/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
@RunWith(AndroidJUnit4::class)
class InputActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(InputActivity::class.java)

    private fun onEditText(matcher: Matcher<View>) =
            onView(allOf(
                    isDescendantOfA(matcher),
                    isAssignableFrom(EditText::class.java)))

    @Test
    fun shouldCellEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cellPhoneEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(click())

        onEditText(withId(R.id.cellPhoneEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.cellPhoneEditText))
                .perform(replaceText("00000000000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cellPhoneEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("(00) 00000-0000")))
    }

    @Test
    fun shouldPhoneEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.PhoneEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.PhoneEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.PhoneEditText))
                .perform(replaceText("0000000000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.PhoneEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("(00) 0000-0000")))
    }

    @Test
    fun shouldCNPJEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cnpjEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cnpjEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.cnpjEditText))
                .perform(scrollTo(), replaceText("00000000000000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cnpjEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("00.000.000/0000-00")))
    }

    @Test
    fun shouldCPFEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cpfEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cpfEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.cpfEditText))
                .perform(scrollTo(), replaceText("00000000000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cpfEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("000.000.000-00")))
    }

    @Test
    fun shouldEmailEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.emailEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("aaa"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.emailEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Email inválido")))

        onEditText(withId(R.id.emailEditText))
                .perform(scrollTo(), replaceText("email@email.com"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.emailEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("email@email.com")))
    }

    @Test
    fun shouldCepEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cepEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cepEditText))
                .check(
                        matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.cepEditText))
                .perform(scrollTo(), replaceText("21000000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.cepEditText))
                .perform(scrollTo(), click())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("")))
                .check(matches(withText("21000-000")))
    }

    @Test
    fun shouldCurrencyEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.currencyEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), typeText("-12"))
                .check(matches(withText("$0.12")))

        onEditText(withId(R.id.currencyEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), replaceText("12.12.12"))
                .check(matches(withText("$1,212.12")))

        onEditText(withId(R.id.currencyEditText))
                .perform(scrollTo(), click())
                .perform(scrollTo(), replaceText(""))
                .check(matches(withText("$0.00")))
    }
}