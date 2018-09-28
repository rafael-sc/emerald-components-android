package br.com.stone.emeraldcomponentsandroid

import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity

/**
 * Created by lucasdiego on 27/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
abstract class BaseScreenshotTest {

    abstract val activity: AppCompatActivity

    protected inline fun <reified T: AppCompatActivity> activityTestRule() =
            ActivityTestRule(T::class.java)

    fun screenShot(description: String = "", fromActivity: AppCompatActivity? = activity) {
        fromActivity?.let {
            //implements screenshot from chosen lib
        }
    }
}