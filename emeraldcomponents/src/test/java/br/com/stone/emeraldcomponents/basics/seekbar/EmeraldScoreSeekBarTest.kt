package br.com.stone.emeraldcomponents.basics.seekbar

import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.seekbar.EmeraldScoreSeekBar
import br.com.stone.emeraldcomponents.basic.seekbar.ScoreSeekBarEnum
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Created by daniele.freitas on 29/04/20.
 * Copyright (c) Stone Co. All rights reserved.
 * daniele.freitas@stone.com.br
 */

@RunWith(RobolectricTestRunner::class)
class EmeraldScoreSeekBarTest {

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldScoreSeekBar(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .build()
        val view = EmeraldScoreSeekBar(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun testProgress() {
        var scoreSeekBar = EmeraldScoreSeekBar(ApplicationProvider.getApplicationContext())
        var calledMethod = false
        scoreSeekBar.onProgressChanged = { calledMethod = true }
        scoreSeekBar.progress = 5
        Assert.assertTrue(calledMethod)
    }

    @Test
    fun testGetScoreItemByProgressProgress() {
        val scoreItem = ScoreSeekBarEnum.getScoreImageByProgress(5)
        Assert.assertEquals(ScoreSeekBarEnum.SCORE_FIVE, scoreItem)
    }

    @Test
    fun testGetScoreImageByProgressProgress() {
        val scoreImage = ScoreSeekBarEnum.getScoreImageByProgress(5)
        Assert.assertEquals(R.drawable.ic_selector_score_five, scoreImage.iconId)
    }
}