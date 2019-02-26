package br.com.stone.emeraldcomponentsandroid.activities

import androidx.appcompat.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import org.junit.Rule
import org.junit.Test

/**
 * Created by renan.silva on 24/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class LabelActivityTest : BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    var activityRule = activityTestRule<LabelActivity>()

    @Test
    fun shouldLabelsBeShown() {
        screenShot("emerald-label")
    }

}