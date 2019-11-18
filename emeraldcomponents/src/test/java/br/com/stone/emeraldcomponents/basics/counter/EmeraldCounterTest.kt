package br.com.stone.emeraldcomponents.basics.counter

import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.counter.EmeraldCounter
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
}