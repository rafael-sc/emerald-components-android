package br.com.stone.emeraldcomponentsandroid

import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity

/**
 * Created by lucasdiego on 27/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
abstract class BaseScreenshotTest {

    protected var activity: AppCompatActivity? = null

    protected inline fun <reified T: AppCompatActivity> activityTestRule(): ActivityTestRule<T> {
        val rule = ActivityTestRule(T::class.java)
        activity = rule.activity
        return rule
    }

    fun screenShot(description: String = "", fromActivity: AppCompatActivity? = activity) {
        fromActivity?.let {
            //implements screenshot from chosen lib
        }
    }

}