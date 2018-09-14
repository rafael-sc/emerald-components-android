package br.com.stone.emeraldcomponentsandroid

import android.view.View
import android.view.ViewGroup
import br.com.stone.emeraldcomponents.basic.input.EmeraldMaskedEditText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Created by lucasdiego on 11/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
object CustomMatcher {

    fun hasTextInputLayoutErrorText(expectedErrorText: String) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {}

        override fun matchesSafely(item: View?): Boolean {
            if (item !is EmeraldMaskedEditText) {
                return false
            }

            val error = item.errorMessage ?: return false
            return expectedErrorText == error
        }
    }

    fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}