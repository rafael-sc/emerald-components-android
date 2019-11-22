package br.com.stone.emeraldcomponents.basics.counter

import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.counter.EmeraldCounter
import kotlinx.android.synthetic.main.widget_counter.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmeraldCounterTest {

    lateinit var counter: EmeraldCounter

    @Before
    fun setup() {
        counter = EmeraldCounter(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldCounter(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .build()
        val view = EmeraldCounter(ApplicationProvider.getApplicationContext(), attrs)
        Assert.assertNotNull(view)
    }

    @Test
    fun testSetParam() {
        val expectedValue = 1
        counter.setup(0, 3, expectedValue)
        Assert.assertEquals(expectedValue, counter.counter)
    }

    @Test
    fun testMinusSign() {
        val expectedValue = 0
        val startValue = 1
        counter.setup(0, 3, startValue)
        counter.minusSign.performClick()
        Assert.assertEquals(expectedValue, counter.counter)
    }

    @Test
    fun testPlusSign() {
        val expectedValue = 2
        val startValue = 1
        counter.setup(0, 3, startValue)
        counter.plusSign.performClick()
        Assert.assertEquals(expectedValue, counter.counter)
    }

    @Test
    fun testMinusSignExceed() {
        val expectedValue = 0
        val startValue = 0
        counter.setup(0, 3, startValue)
        if (counter.minusSign.isEnabled) //performClick() does not verify it
            counter.minusSign.performClick()
        Assert.assertEquals(expectedValue, counter.counter)
    }

    @Test
    fun testPlusSignExceed() {
        val expectedValue = 3
        val startValue = 3
        counter.setup(0, 3, startValue)
        if (counter.plusSign.isEnabled) //performClick() does not verify it
            counter.plusSign.performClick()
        Assert.assertEquals(expectedValue, counter.counter)
    }
}