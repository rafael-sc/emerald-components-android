package br.com.stone.emeraldcomponentsandroid.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import br.com.stone.emeraldcomponentsandroid.R
import org.junit.Rule
import org.junit.Test

/**
 * Created by renan.silva on 28/01/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class PagerActivityTest : BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    var activityRule = activityTestRule<PagerActivity>()

    @Test
    fun shouldSwipeBulletPager() {
        onView(withId(R.id.emeraldBulletPager))
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeRight())
                .perform(swipeRight())
                .perform(swipeRight())
                .perform(swipeRight())

        screenShot("emerald-bullet-pager")
    }

    @Test
    fun shouldSwipeTabPager() {
        onView(withId(R.id.emeraldTabPager))
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeLeft())
                .perform(swipeRight())
                .perform(swipeRight())
                .perform(swipeRight())
                .perform(swipeRight())

        screenShot("emerald-tab-pager")
    }

}