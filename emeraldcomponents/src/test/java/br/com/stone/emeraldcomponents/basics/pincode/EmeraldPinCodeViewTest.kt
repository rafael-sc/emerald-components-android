package br.com.stone.emeraldcomponents.basics.pincode

import androidx.test.core.app.ApplicationProvider
import br.com.stone.emeraldcomponents.basic.pinview.EmeraldPinCodeView
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmeraldPinCodeViewTest {

    @Test
    fun `test create pin code`() {
        val view = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        Assert.assertNotNull(view)
    }

    @Test
    fun `test set code`() {
        val emeraldPinCode = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        val itemCount = 6
        emeraldPinCode.init(true, itemCount)
        val textToPaste = "123456"
        emeraldPinCode.setCode(textToPaste)
        Assert.assertTrue(emeraldPinCode.getCode() == textToPaste)
    }

    @Test
    fun `test correct amount of items created`() {
        val emeraldPinCode = EmeraldPinCodeView(ApplicationProvider.getApplicationContext())
        var itemCount = 6
        emeraldPinCode.init(true, itemCount)
        Assert.assertEquals(emeraldPinCode.childCount, itemCount)
        itemCount = 3
        emeraldPinCode.init(false, itemCount)
        Assert.assertEquals(emeraldPinCode.childCount, itemCount)
    }
}